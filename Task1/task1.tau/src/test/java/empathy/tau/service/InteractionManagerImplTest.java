package empathy.tau.service;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import java.util.Date;

import empathy.tau.domain.Interaction;

@RunWith(MockitoJUnitRunner.class)
public class InteractionManagerImplTest {
     
    @Mock 
    TimeSource timeSource;

    @InjectMocks
    InteractionManagerImpl interactionManager;
    Interaction interaction;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        interactionManager = new InteractionManagerImpl();
        interaction = new Interaction();
        
    }   

    @Test(expected=IllegalArgumentException.class)
    public void createArgumentCannotBeNull() {
        interactionManager.create(null);
    }

    @Test
    public void createInteractionReturnsId() {
        Integer expectedId = interactionManager.dataBase.size();
        assertEquals(expectedId, interactionManager.create(interaction));
    }

    @Test(expected=IllegalArgumentException.class)
    public void getIdCannotBeNull() {
        interactionManager.getById(null);
    }

    @Test
    public void returnInteractionById(){
        Interaction exemple = new Interaction();
        interactionManager.create(exemple);
        assertEquals(exemple, interactionManager.getById(0));
    }

    @Test(expected=IllegalArgumentException.class)
    public void deleteIdCannotBeNull() {
        interactionManager.delete(null);
    }

    @Test
    public void interactionSuccesfullyDeleted() {
        Interaction exemple = new Interaction();
        interactionManager.create(exemple);
        interactionManager.delete(0);
        assertEquals(0,interactionManager.dataBase.size());
    }

    @Test(expected=IllegalArgumentException.class)
    public void updateIdCannotBeNull() {
        Interaction exemple = new Interaction();
        interactionManager.update(null, exemple);
    }

    @Test(expected=IllegalArgumentException.class)
    public void updateInteractionCannotBeNull() {
        Interaction exemple = new Interaction();
        interactionManager.create(exemple);
        interactionManager.update(0, null);
    }

    @Test
    public void interactionCorrectlyUpdated() {
        Interaction exemple = new Interaction();
        interactionManager.create(exemple);
        Interaction updatedExemple = new Interaction();
        interactionManager.update(0, updatedExemple);
        assertEquals(updatedExemple, interactionManager.getById(0));
        
    }

    @Test
    public void getAllReturnZeroIfEmpty() {
        assertEquals(0, interactionManager.getAll().size());
    }
    
    @Test
    public void getAllReturnsAllInteractions() {
        Interaction exemple1 = new Interaction();
        interactionManager.create(exemple1);
        Interaction exemple2 = new Interaction();
        interactionManager.create(exemple2);
        assertEquals(2, interactionManager.getAll().size());
    }

    @Test
    public void getAllReturnsCorrectInteractions(){
        Interaction exemple1 = new Interaction();
        interactionManager.create(exemple1);
        Interaction exemple2 = new Interaction();
        interactionManager.create(exemple2);
        assertEquals(exemple1, interactionManager.getById(0));
        assertEquals(exemple2, interactionManager.getById(1));
    }
    

    @Test
    public void isReadTimeSavedIfRequired() {
        when(timeSource.getCurrentDate()).thenReturn(new Date(118,10,4));
        interactionManager.db.setTimeSource(timeSource);
        Integer addedId = interactionManager.create(interaction);
        interactionManager.getById(addedId);
        assertEquals(new Date(118,10,4), interactionManager.readTimes.get(addedId));
    }

    @Test
    public void isCreateTimeSavedIfRequired() {
        when(timeSource.getCurrentDate()).thenReturn(new Date(118,10,4));
        interactionManager.db.setTimeSource(timeSource);
        Integer addedId = interactionManager.create(interaction);
        assertEquals(new Date(118,10,4), interactionManager.createTimes.get(addedId));
    }

    @Test
    public void isUpdateTimeSavedIfRequired() {
        when(timeSource.getCurrentDate()).thenReturn(new Date(118,10,4));
        interactionManager.db.setTimeSource(timeSource);
        Interaction exemple = new Interaction();
        Integer createdId = interactionManager.create(exemple);
        Interaction updatedExemple = new Interaction();
        interactionManager.update(createdId, updatedExemple);
        assertEquals(new Date(118,10,4), interactionManager.updateTimes.get(createdId));
    }

    @Test
    public void isGetTimeInfoWorkingForCreate() {
        when(timeSource.getCurrentDate()).thenReturn(new Date(118,10,4));
        interactionManager.db.setTimeSource(timeSource);
        Integer addedId = interactionManager.create(interaction);
        assertEquals(new Date(118,10,4), interactionManager.getTimeInfoById(addedId)[0]);
    }

    @Test
    public void isGetTimeInfoWorkingForRead() {
        when(timeSource.getCurrentDate()).thenReturn(new Date(118,10,4));
        interactionManager.db.setTimeSource(timeSource);
        Integer addedId = interactionManager.create(interaction);
        interactionManager.getById(addedId);
        assertEquals(new Date(118,10,4), interactionManager.getTimeInfoById(addedId)[1]);
    }

    @Test
    public void isGetTimeInfoWorkingForUpdate() {
        when(timeSource.getCurrentDate()).thenReturn(new Date(118,10,4));
        interactionManager.db.setTimeSource(timeSource);
        Interaction exemple = new Interaction();
        Integer createdId = interactionManager.create(exemple);
        Interaction updatedExemple = new Interaction();
        interactionManager.update(createdId, updatedExemple);
        assertEquals(new Date(118,10,4), interactionManager.getTimeInfoById(createdId)[2]);
    }

    @Test
    public void isTurnOffCreateTimeSaveWorking() {
        when(timeSource.getCurrentDate()).thenReturn(new Date(118,10,4));
        interactionManager.db.setTimeSource(timeSource);
        interactionManager.turnOffSaveCreateTimes();
        Integer addedId = interactionManager.create(interaction);
        assertNull(interactionManager.getTimeInfoById(addedId)[0]);
    }
    
    @Test
    public void isTurnOffReadTimeSaveWorking() {
        when(timeSource.getCurrentDate()).thenReturn(new Date(118,10,4));
        interactionManager.db.setTimeSource(timeSource);
        interactionManager.turnOffSaveReadTimes();
        Integer addedId = interactionManager.create(interaction);
        interactionManager.getById(addedId);
        assertNull(interactionManager.getTimeInfoById(addedId)[1]);
    }

    @Test
    public void isTurnOffUpdateTimeSaveWorking() {
        when(timeSource.getCurrentDate()).thenReturn(new Date(118,10,4));
        interactionManager.db.setTimeSource(timeSource);
        interactionManager.turnOffSaveUpdateTimes();
        Interaction exemple = new Interaction();
        Integer createdId = interactionManager.create(exemple);
        Interaction updatedExemple = new Interaction();
        interactionManager.update(createdId, updatedExemple);
        assertNull(interactionManager.getTimeInfoById(createdId)[2]);
    }

    @Test
    public void isTurnOnSaveCreateTimeWorking() {
        when(timeSource.getCurrentDate()).thenReturn(new Date(118,10,4));
        interactionManager.db.setTimeSource(timeSource);
        interactionManager.saveCreateTimes = false;
        interactionManager.turnOnSaveCreateTimes();
        Integer addedId = interactionManager.create(interaction);
        assertEquals(new Date(118,10,4), interactionManager.getTimeInfoById(addedId)[0]);
    }

    @Test
    public void isTurnOnSaveReadTimeWorking() {
        when(timeSource.getCurrentDate()).thenReturn(new Date(118,10,4));
        interactionManager.db.setTimeSource(timeSource);
        interactionManager.saveReadTimes = false;
        interactionManager.turnOnSaveReadTimes();
        Integer addedId = interactionManager.create(interaction);
        interactionManager.getById(addedId);
        assertEquals(new Date(118,10,4), interactionManager.getTimeInfoById(addedId)[1]);
    }

    @Test
    public void isTurnOnSaveUpdateTimeWorking() {
        when(timeSource.getCurrentDate()).thenReturn(new Date(118,10,4));
        interactionManager.db.setTimeSource(timeSource);
        interactionManager.saveUpdateTimes = false;
        interactionManager.turnOnSaveUpdateTimes();
        Interaction exemple = new Interaction();
        Integer createdId = interactionManager.create(exemple);
        Interaction updatedExemple = new Interaction();
        interactionManager.update(createdId, updatedExemple);
        assertEquals(new Date(118,10,4), interactionManager.getTimeInfoById(createdId)[2]);
    }
}