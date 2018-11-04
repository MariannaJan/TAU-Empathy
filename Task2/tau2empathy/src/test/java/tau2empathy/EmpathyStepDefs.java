package tau2empathy;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import tau2empathy.domain.Interaction;
import tau2empathy.service.InteractionManagerImpl;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import java.util.List;

public class EmpathyStepDefs {

    private InteractionManagerImpl manager;
    private List<Integer> searchedIds;

    @Before
    public void setUp() {
        manager = new InteractionManagerImpl();
    }

    @Given("^we have one interaction in db with description containing 'default'$")
    public void we_have_one_interaction_in_db_with_description_containing_default() throws Exception {
        Interaction interaction = new Interaction();
        interaction.interactionDescription = "This is default description.";
        manager.create(interaction);
    }

    @When("^we search by regex for 'default'$")
    public void we_search_by_regex_for_default() throws Exception {
        searchedIds = manager.searchByRegex("default");
    }

    @Then("^we get list with one interaction id$")
    public void we_get_list_with_one_interaction_id() throws Exception {
        assertEquals(1, searchedIds.size());
    }    
}