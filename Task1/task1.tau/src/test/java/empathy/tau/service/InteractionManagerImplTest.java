package empathy.tau.service;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import empathy.tau.service.IInteractionManager;

//@RunWith(JUnit4.class)
public class InteractionManagerImplTest {

    IInteractionManager interactionManager;

    @Before
    public void before() {
        interactionManager = new InteractionManagerImpl();
    }   

    @Test(expected=IllegalArgumentException.class)
    public void createArgumentCannotBeNull() {
        interactionManager.create(null);
    }
}