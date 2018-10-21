package empathy.tau.service;

import empathy.tau.domain.Interaction;
import java.util.ArrayList;
import java.util.List;

public class InteractionManagerImpl implements IInteractionManager {

    public List<Interaction> dataBase;

    InteractionManagerImpl() {
        dataBase = new ArrayList<>();
    }

    public Integer create (Interaction interaction) {
        if (interaction == null) {
            throw new IllegalArgumentException("Create argument cannot be null");
        }
        dataBase.add(interaction);
        Integer interactionIndex = dataBase.size()-1;
        return interactionIndex;
        
    }
    
    public void update (Integer id, Interaction interaction) {
        if (id  == null || interaction == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        else {
            if (id < dataBase.size()) {
                dataBase.set(id, interaction);
            }
        }
     }

    public void delete (Integer id) {
        if (id  == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        else {
            this.dataBase.remove(id.intValue());
        }
     }

    public Interaction getById (Integer id) {
        if (id  == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        else {
            return dataBase.get(id);
        }
        
    }

    public List<Interaction> getAll () {
        return dataBase;
    }
}