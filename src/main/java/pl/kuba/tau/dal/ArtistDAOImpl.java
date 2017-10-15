package pl.kuba.tau.dal;

import pl.kuba.tau.domain.Artist;
import pl.kuba.tau.exception.DAOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistDAOImpl extends AbstractDAO<Artist> implements ArtistDAO {

    private final Map<Integer, Artist> artists = new HashMap<>();

    @Override
    public Artist create(Artist a) {
        if (a == null) {
            return null;
        }
        artists.put(a.getId(), a);
        return clone(a);
    }

    @Override
    public List<Artist> list() {
        List<Artist> resp = new ArrayList<>();
        artists.values().forEach(a -> resp.add(clone(a)));
        return resp;
    }

    @Override
    public Artist get(int id) {
        if (!artists.containsKey(id)) {
            return null;
        }
        return clone(artists.get(id));
    }

    @Override
    public Artist update(Artist a) throws DAOException {
        if (!artists.containsKey(a.getId())) {
            throw new DAOException(DAOException.ErrorCode.ENTITY_NOT_FOUND);
        }
        artists.put(a.getId(), a);
        return clone(artists.get(a.getId()));
    }

    @Override
    public void delete(Artist a) {
        artists.remove(a.getId());
    }
}
