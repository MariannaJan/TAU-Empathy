package empathy.tau.service;
import java.util.Date;
import java.util.HashMap; 
import empathy.tau.domain.Interaction;

class DataBaseImpl {
    
    Integer autoId;

    public HashMap<Integer, Interaction> dataBase; 
    public HashMap<Integer, Date> readTimes;
    public HashMap<Integer, Date> createTimes;
    public HashMap<Integer, Date> updateTimes;
    public TimeSource timeSource;

    DataBaseImpl() {
        this.autoId = 0;
        this.dataBase = new HashMap<>();
        this.readTimes = new HashMap<>();
        this.createTimes = new HashMap<>();
        this.updateTimes = new HashMap<>();
        this.timeSource = new TimeSource(){
        
            @Override
            public Date getCurrentDate() {
                return new Date();
            }
        };
    }

    void setTimeSource(TimeSource ts) {
        this.timeSource= ts;
    }

}