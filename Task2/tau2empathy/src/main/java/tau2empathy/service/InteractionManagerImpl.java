package tau2empathy.service;
import tau2empathy.domain.Interaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class InteractionManagerImpl implements IInteractionManager {

    public DataBaseImpl db;
    public HashMap<Integer, Interaction> dataBase;

    public HashMap<Integer, Date> readTimes;
    public HashMap<Integer, Date> createTimes;
    public HashMap<Integer, Date> updateTimes;

    public boolean saveReadTimes;
    public boolean saveCreateTimes;
    public boolean saveUpdateTimes; 


    public InteractionManagerImpl() {
        this.saveReadTimes = true;
        this.saveCreateTimes = true;
        this.saveUpdateTimes = true;
        db = new DataBaseImpl();
        dataBase = db.dataBase;
    }

    public void turnOnSaveReadTimes() {
        this.saveReadTimes = true;
    }
    public void turnOffSaveReadTimes() {
        this.saveReadTimes = false;
    }

    public void turnOnSaveCreateTimes() {
        this.saveCreateTimes = true;
    }

    public void turnOffSaveCreateTimes() {
        this.saveCreateTimes = false;
    }

    public void turnOnSaveUpdateTimes() {
        this.saveUpdateTimes = true;
    }

    public void turnOffSaveUpdateTimes() {
        this.saveUpdateTimes = false;
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

    public Date[] getTimeInfoById(Integer id) {
        Date timeInfo[] = new Date[3];
            timeInfo[0] = db.createTimes.get(id);
            timeInfo[1] = db.readTimes.get(id);
            timeInfo[2] = db.updateTimes.get(id);
        return timeInfo;
    }

    public List<Integer> searchByRegex(String regex) {
        List<Integer> interactionIds = new ArrayList<Integer>();
        return interactionIds;
    }
}