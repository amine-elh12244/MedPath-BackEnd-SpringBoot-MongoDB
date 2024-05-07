package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.Config.JwtUtil;
import agil.teem.harmoniacarebackend.entities.JwtRequest;
import agil.teem.harmoniacarebackend.entities.JwtResponse;
import agil.teem.harmoniacarebackend.entities.user;
import agil.teem.harmoniacarebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public JwtService(UserRepository userRepository,
                      JwtUtil jwtUtil,
                      AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        final UserDetails userDetails = loadUserByUsername(userName);

        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        user user = userRepository.findById(userName).orElseThrow(() ->
                new UsernameNotFoundException("Username is not valid"));

        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user user = userRepository.findById(username).orElseThrow(() ->
                new UsernameNotFoundException("Username is not valid"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                getAuthorities(user));
    }

    private Set<SimpleGrantedAuthority> getAuthorities(user user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role ->
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));

        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("User is disabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials");
        }
    }
}
