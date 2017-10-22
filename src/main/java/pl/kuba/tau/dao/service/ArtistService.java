package pl.kuba.tau.dao.service;

import pl.kuba.tau.dao.ArtistDAO;
import pl.kuba.tau.domain.Artist;
import pl.kuba.tau.exception.DAOException;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ArtistService {

    private ArtistDAO dao;

    public void setDataSource(ArtistDAO dao) {
        this.dao = dao;
    }

    public List<Artist> findRecordsByRegex(String regexString) {
        List<Artist> list = dao.list();

        for (Iterator<Artist> it = list.iterator(); it.hasNext(); ) {
            Artist a = it.next();
            if (!a.getName().matches(regexString)) {
                it.remove();
            }
        }
        return list;
    }

    public void deleteRecords(Set<Artist> objectsToRemove) throws DAOException {
        for (Artist a : objectsToRemove) {
            dao.delete(a);
        }
    }
}
