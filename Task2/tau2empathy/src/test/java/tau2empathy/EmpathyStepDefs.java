package tau2empathy;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import tau2empathy.domain.Interaction;
import tau2empathy.service.InteractionManagerImpl;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import java.util.ArrayList;
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
    
    @Given("^we have interaction in db with not matching description$")
    public void we_have_interaction_in_db_with_not_matching_description() throws Exception {
        Interaction interaction = new Interaction();
        interaction.interactionDescription = "This is default description.";
        manager.create(interaction);
    }

    @When("^we search by regex for '(\\d+)'$")
    public void we_search_by_regex_for(int arg1) throws Exception {
        searchedIds = manager.searchByRegex("12345");
    }

    @Then("^we get empty list of interactionIds$")
    public void we_get_empty_list_of_interactionIds() throws Exception {
        assertEquals(0, searchedIds.size());
    }

    @Given("^we have four interactions in db$")
    public void we_have_four_interactions_in_db() throws Exception {
        for (int i = 0; i <4; i++) {
            Interaction interaction = new Interaction();
            interaction.empathyValue = i;
            manager.create(interaction);
        }
    }

    @When("^we delete two of them:$")
    public void we_delete_two_of_them(List<Integer> arg1) throws Exception {
        List<Integer> ids = arg1;
        manager.deleteInteractionsByList(ids);
    }

    @Then("^we have exactly two remaining interactions$")
    public void we_have_exactly_two_remaining_interactions() throws Exception {
        assertEquals(2, manager.dataBase.size());
    }


    @When("^we try to delete interaction with id:$")
    public void we_try_to_delete_interaction_with_id(List<Integer> arg1) throws Exception {
        List<Integer> ids = arg1;
        manager.deleteInteractionsByList(ids);
    }

    @Then("^we still have four interactions in db$")
    public void we_still_have_four_interactions_in_db() throws Exception {
        assertEquals(4, manager.dataBase.size());
    }
}