package pl.kuba.tau.dal;

import java.util.List;
import pl.kuba.tau.domain.Artist;

public interface ArtistDAO {

    Artist create(Artist a);

    List<Artist> list();

    Artist get(int id);

    Artist update(Artist a);

    void delete(Artist a);
}
