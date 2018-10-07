package empathy.tau;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
class InteractionManagerImplTest {

    IInteractionManager interactionManage;

    @Before
    public void before() {
        interactionManager = new InteractionManagerImpl();
    }   

    @Test(expected=IllegalArgumentException.class)
    public void createArgumentCannotBeNull() {
    }
}