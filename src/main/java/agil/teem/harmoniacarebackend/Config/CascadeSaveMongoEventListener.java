package agil.teem.harmoniacarebackend.Config;

import agil.teem.harmoniacarebackend.entities.Request;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class CascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        if (source instanceof Request) {
            Request request = (Request) source;
            if (request.getPatient() != null) {
                mongoOperations.save(request.getPatient());
            }
            if (request.getMedecin() != null) {
                mongoOperations.save(request.getMedecin());
            }
            if (request.getTest() != null) {
                mongoOperations.save(request.getTest());
            }
            if (request.getCourier() != null) {
                mongoOperations.save(request.getCourier());
            }
            if (request.getLab() != null) {
                mongoOperations.save(request.getLab());
            }

            if (request.getResult() != null)
            { mongoOperations.save(request.getResult()); }


        }
    }
}