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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteRecordsStepsTest {

    private ArtistDAO dao;
    private Artist artist1 = new Artist(99, "Joe", 1923);
    private List<Artist> artists = new ArrayList<>();
    private ArtistService service;

    @Before
    public void setup() {
        dao = new ArtistDAOImpl();
        service = new ArtistService();
        service.setDataSource(dao);
        dao.create(artist1);
        artists.add(artist1);
    }

    @Given("^we add artis with (\\d+), \"([^\"]*)\" and (\\d+)$")
    public void we_have_artis_with_and(int id, String name, int birthYear) throws Throwable {
        Artist a = dao.create(new Artist(id, name, birthYear));
        Assert.assertEquals(id, a.getId());
        Assert.assertEquals(birthYear, a.getBirthYear());
        Assert.assertEquals(name, a.getName());

        artists.add(a);
        Assert.assertEquals(2, artists.size());
        Assert.assertEquals(2, dao.list().size());
    }

    @When("^we put his (\\d+) to a removal list$")
    public void we_put_one_of_them_with_id_to_a_removal_list(int id) throws Throwable {
        Artist artist = dao.get(id);
        Assert.assertNotNull(artist);

        service.deleteRecords(Collections.singleton(artist));
        artists.remove(artist);
    }

    @Then("^he should not be visible in db$")
    public void he_should_not_be_visible_in_db() throws Throwable {
        Assert.assertEquals(1, dao.list().size());
        Assert.assertNotEquals(artist1.getName(), dao.list().get(0));
    }

    @Then("^the other one should stay$")
    public void the_other_one_should_stay() throws Throwable {
        Assert.assertEquals(1, artists.size());
        Assert.assertEquals(artists.get(0), dao.list().get(0));
    }
}
