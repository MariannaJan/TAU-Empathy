package empathy.tau.service;

import empathy.tau.domain.Interaction;

public interface IInteractionManager {
    
    public void create (Interaction interaction);

    public void update (Interaction interaction);

    public void delete (Interaction interaction);
}