package pl.kuba.tau.dal;

import pl.kuba.tau.domain.Artist;
import pl.kuba.tau.exception.DAOException;

import java.util.List;

public interface ArtistDAO {

    Artist create(Artist a);

    List<Artist> list();

    Artist get(int id);

    Artist update(Artist a) throws DAOException;

    void delete(Artist a);
}
