package empathy.tau.service;

import empathy.tau.domain.Interaction;
import empathy.tau.service.IInteractionManager;

public class InteractionManagerImpl implements IInteractionManager {

    public void create (Interaction interaction) {
        if (interaction == null) {
            throw new IllegalArgumentException("Create argument cannot be null");
        }
    }
    
    public void update (Interaction interaction) { }

    public void delete (Interaction interaction) { }
}