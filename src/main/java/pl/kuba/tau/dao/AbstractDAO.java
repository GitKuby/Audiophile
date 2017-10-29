package pl.kuba.tau.dao;

import org.apache.commons.lang.SerializationUtils;
import pl.kuba.tau.domain.AbstractEntity;

class AbstractDAO<T extends AbstractEntity> {

    @SuppressWarnings("unchecked")
    T clone(T t) {
        return (T) (SerializationUtils.clone(t));
    }
}
