package pl.kuba.tau.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kuba.tau.dao.service.ArtistService;
import pl.kuba.tau.domain.Artist;
import pl.kuba.tau.exception.DAOException;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArtistDAOMockTest {

    private ArtistService service = new ArtistService();
    private Artist a1 = new Artist(1, "Adam", 1960);
    private Artist a2 = new Artist(2, "John", 1977);
    private Artist a3 = new Artist(3, "Eddy", 1968);

    @Mock
    private ArtistDAO mockedDao;

    @Before
    public void setup() {
        assertNotNull(mockedDao);
        service.setDataSource(mockedDao);
    }

    @Test
    public void testDeleteRecords() throws DAOException {

        Set<Artist> set = new HashSet<>();
        set.add(a1);
        set.add(a2);

        service.deleteRecords(set);
        verify(mockedDao, times(2)).delete(any(Artist.class));
        verify(mockedDao, times(1)).delete(a1);
        verify(mockedDao, times(1)).delete(a2);
    }

    @Test(expected = DAOException.class)
    public void testExceptionInDeleteRecords() throws DAOException {
        doThrow(new DAOException(DAOException.ErrorCode.ENTITY_NOT_FOUND))
                .when(mockedDao).delete(any(Artist.class));
        service.deleteRecords(Collections.singleton(a1));
    }

    @Test
    public void testFindRecordsByRegex() {

        List<Artist> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        list.add(a3);

        when(mockedDao.list()).thenReturn(list);

        List<Artist> artists = service.findRecordsByRegex("Jo..");
        assertNotNull(artists);
        assertEquals(1, artists.size());
        assertEquals("John", artists.get(0).getName());
        assertEquals(2, artists.get(0).getId());
        assertEquals(1977, artists.get(0).getBirthYear());

        artists.clear();
        artists = service.findRecordsByRegex("Ahm*");
        assertNotNull(artists);
        assertEquals(0, artists.size());

        verify(mockedDao, times(2)).list();
    }
}
