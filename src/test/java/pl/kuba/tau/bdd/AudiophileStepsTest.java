package pl.kuba.tau.bdd;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pl.kuba.tau.dao.ArtistDAO;
import pl.kuba.tau.dao.ArtistDAOImpl;
import pl.kuba.tau.domain.Artist;

public class AudiophileStepsTest {

    private ArtistDAO dao;
    private Artist a;

    @Before
    public void setup() {
        dao = new ArtistDAOImpl();
        a = new Artist(1, "John", 1990);
    }

    @Given("^there is one artist$")
    public void there_is_one_artist() throws Throwable {

        Artist saved = dao.create(a);
        Assert.assertEquals(a.getName(), saved.getName());
        Assert.assertEquals(a.getBirthYear(), saved.getBirthYear());
        Assert.assertEquals(a.getId(), saved.getId());
    }

    @When("^begginning of his name is passed as \"([^\"]*)\"$")
    public void begginning_of_his_is_passed_as(String regex) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^we should find this artist$")
    public void we_should_find_this_artist() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
