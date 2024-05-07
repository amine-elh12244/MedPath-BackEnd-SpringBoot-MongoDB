package agil.teem.harmoniacarebackend.Config;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;
import agil.teem.harmoniacarebackend.entities.Role;
import agil.teem.harmoniacarebackend.repositories.RoleRepository;

@Component
@ReadingConverter
public class RoleReaderConverter implements Converter<String, Role> {

    private final RoleRepository roleRepository;

    public RoleReaderConverter(@Lazy RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role convert(String source) {
        return roleRepository.findById(source).orElse(null);
    }
}