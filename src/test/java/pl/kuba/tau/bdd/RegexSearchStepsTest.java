package pl.kuba.tau.bdd;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pl.kuba.tau.dao.ArtistDAO;
import pl.kuba.tau.dao.ArtistDAOImpl;
import pl.kuba.tau.dao.service.ArtistService;
import pl.kuba.tau.domain.Artist;

import java.util.List;

public class RegexSearchStepsTest {

    private ArtistDAO dao;
    private Artist artist1;
    private Artist artist2;
    private List<Artist> artists;

    @Before
    public void setup() {
        dao = new ArtistDAOImpl();
        artist1 = new Artist(1, "John", 1990);
        artist2 = new Artist(2, "Eddy", 1954);
    }

    @Given("^there is one artist$")
    public void there_is_one_artist() throws Throwable {
        Artist saved = dao.create(artist1);
        Assert.assertEquals(artist1.getName(), saved.getName());
        Assert.assertEquals(artist1.getBirthYear(), saved.getBirthYear());
        Assert.assertEquals(artist1.getId(), saved.getId());

        Artist saved2 = dao.create(artist2);
        Assert.assertEquals(artist2.getName(), saved2.getName());
        Assert.assertEquals(artist2.getBirthYear(), saved2.getBirthYear());
        Assert.assertEquals(artist2.getId(), saved2.getId());
    }

    @Given("^another one with different name$")
    public void another_one_with_different_name() throws Throwable {

    }

    @When("^begginning of his name is passed as \"([^\"]*)\"$")
    public void begginning_of_his_is_passed_as(String regex) throws Throwable {
        ArtistService service = new ArtistService();
        service.setDataSource(dao);
        artists = service.findRecordsByRegex(regex);
        Assert.assertNotNull(artists);
    }

    @Then("^we should find this artist$")
    public void we_should_find_this_artist() throws Throwable {
        Assert.assertEquals(1, artists.size());
        Artist john = artists.get(0);
        Assert.assertEquals(artist1.getName(), john.getName());
        Assert.assertEquals(artist1.getId(), john.getId());
        Assert.assertEquals(artist1.getBirthYear(), john.getBirthYear());
    }

    @Then("^I should not see more than one artist$")
    public void i_should_not_see_more_than_one_artist() throws Throwable {
        Assert.assertEquals(1, artists.size());
        Assert.assertNotEquals(artists.get(0), artist2);
    }
}
