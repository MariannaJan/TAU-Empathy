package empathy.tau.service;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import empathy.tau.domain.Interaction;

@RunWith(JUnit4.class)
public class InteractionManagerImplTest {

    InteractionManagerImpl interactionManager;
    Interaction interaction;

    @Before
    public void before() {
        interactionManager = new InteractionManagerImpl();
        //interaction = new Interaction("page1", 50, 80, "Interaction", "", 15, 60, "mobile", true, "", false );
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
}