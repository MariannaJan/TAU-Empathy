package empathy.tau.service;
import empathy.tau.domain.Interaction;

import java.util.Date;
import java.util.HashMap; 

public class InteractionManagerImpl implements IInteractionManager {

    public DataBaseImpl db;
    public HashMap<Integer, Interaction> dataBase;

    public HashMap<Integer, Date> readTimes;
    public HashMap<Integer, Date> createTimes;
    public HashMap<Integer, Date> updateTimes;

    public boolean saveReadTimes;
    public boolean saveCreateTimes;
    public boolean saveUpdateTimes; 


    InteractionManagerImpl() {
        this.saveReadTimes = true;
        this.saveCreateTimes = true;
        this.saveUpdateTimes = true;
        db = new DataBaseImpl();
        dataBase = db.dataBase;
    }

    public void toggleSaveReadTimes() {
        this.saveReadTimes = !this.saveReadTimes;
    }

    public void toggleSaveCreateTimes() {
        this.saveCreateTimes = !this.saveCreateTimes;
    }

    public void toggleUpdateTimes() {
        this.saveUpdateTimes = !this.saveUpdateTimes;
    }

    public Integer create (Interaction interaction) {
        if (interaction == null) {
            throw new IllegalArgumentException("Create argument cannot be null");
        }
        Integer interactionIndex = db.autoId;
        db.autoId +=1;
        dataBase.put(interactionIndex, interaction);
        if (saveCreateTimes) {
            createTimes = db.createTimes;
            createTimes.put(interactionIndex, db.timeSource.getCurrentDate());
        }
        return interactionIndex;
        
    }
    
    public void update (Integer id, Interaction interaction) {
        if (id  == null || interaction == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        else {
            if (id < dataBase.size()) {
                dataBase.put(id, interaction);
                if (saveUpdateTimes) {
                    updateTimes = db.updateTimes;
                    updateTimes.put(id, db.timeSource.getCurrentDate());
                }
            }
        }
     }

    public void delete (Integer id) {
        if (id  == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        else {
            this.dataBase.remove(id);
        }
     }

    public Interaction getById (Integer id) {
        if (id  == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        else {
            if (saveReadTimes) {
                readTimes = db.readTimes;
                readTimes.put(id, db.timeSource.getCurrentDate());
            }
            return dataBase.get(id);
        }    
    }

    public HashMap<Integer, Interaction> getAll () {
        return dataBase;
    }
}