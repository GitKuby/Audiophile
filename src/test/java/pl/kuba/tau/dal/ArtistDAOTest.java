package pl.kuba.tau.dal;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.kuba.tau.domain.Artist;
import pl.kuba.tau.exception.DAOException;

public class ArtistDAOTest {

    private static ArtistDAO dao;
    private static Artist testArtist;
    private final static int ID = 1;
    private final static int BIRTH_YEAR = 1961;
    private final static String NAME = "Sting";

    @BeforeClass
    public static void setUp() {
        dao = new ArtistDAOImpl();
        testArtist = new Artist(ID, NAME, BIRTH_YEAR);
    }

    @Before
    public void beforeEveryTest() {
        dao = new ArtistDAOImpl(); //fresh 'db' in every test
    }

    @Test
    public void testCreateArtist() {
        Artist a = dao.create(testArtist);
        assertNotNull(a);
        assertEquals(ID, a.getId());
        assertEquals(NAME, a.getName());
        assertEquals(BIRTH_YEAR, a.getBirthYear());
    }

    @Test
    public void testListArtists() {
        dao.create(testArtist);
        List<Artist> artists = dao.list();
        assertNotNull(artists);
        assertEquals(1, artists.size());
        Artist artist = artists.get(0);
        assertEquals(ID, artist.getId());
        assertEquals(NAME, artist.getName());
        assertEquals(BIRTH_YEAR, artist.getBirthYear());
    }

    @Test
    public void testGetArtist() {
        Artist a = dao.create(testArtist);
        assertNotNull(a);
        Artist artistFromDao = dao.get(a.getId());
        assertNotNull(artistFromDao);
        assertEquals(ID, artistFromDao.getId());
        assertEquals(NAME, artistFromDao.getName());
        assertEquals(BIRTH_YEAR, artistFromDao.getBirthYear());
    }

    @Test
    public void testUpdateArtist() throws DAOException {
        Artist a = dao.create(testArtist);
        assertNotNull(a);
        int newBirthYear = 1980;
        String newName = "Elvis";
        a = dao.update(new Artist(ID, newName, newBirthYear));
        assertNotNull(a);
        assertEquals(ID, a.getId());
        assertEquals(newName, a.getName());
        assertEquals(newBirthYear, a.getBirthYear());
    }

    @Test
    public void testDeleteArtist() {
        Artist a = dao.create(testArtist);
        assertNotNull(a);
        assertNotNull(a);
        dao.delete(a);
        assertEquals(0, dao.list().size());
    }
}
