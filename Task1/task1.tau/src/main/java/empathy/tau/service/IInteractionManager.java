package empathy.tau.service;

import empathy.tau.domain.Interaction;
import java.util.HashMap;


public interface IInteractionManager {

    
    public Integer create (Interaction interaction);

    public void update (Integer id, Interaction interaction);

    public void delete (Integer id);

    public Interaction getById (Integer id);

    public HashMap<Integer, Interaction> getAll ();
}