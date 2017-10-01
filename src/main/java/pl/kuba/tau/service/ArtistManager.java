package pl.kuba.tau.service;

import java.util.List;
import pl.kuba.tau.domain.Artist;

public interface ArtistManager {

    Artist create(Artist a);

    void add(Artist a);

    List<Artist> list();

    Artist get(int id);

    Artist update(Artist a);

    void delete(int id);
}
