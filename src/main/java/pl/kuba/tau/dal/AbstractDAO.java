package pl.kuba.tau.dal;

import org.apache.commons.lang.SerializationUtils;
import pl.kuba.tau.domain.AbstractEntity;

public class AbstractDAO<T extends AbstractEntity> {

    public AbstractDAO(Class entityClass) {
        this.entityClass = entityClass;
    }

    private Class entityClass;

    protected T clone(T t) {
        return (T) (SerializationUtils.clone(t));
    }
}
