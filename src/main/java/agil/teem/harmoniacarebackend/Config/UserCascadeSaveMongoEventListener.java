package agil.teem.harmoniacarebackend.Config;

import agil.teem.harmoniacarebackend.entities.Role;
import agil.teem.harmoniacarebackend.entities.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class UserCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        if (source instanceof user) {
            user user = (user) source;
            if (user.getRole() != null) {
                for (Role role : user.getRole()) {
                    if (role != null) {
                        mongoOperations.save(role);
                    }
                }
            }
        }
    }
}