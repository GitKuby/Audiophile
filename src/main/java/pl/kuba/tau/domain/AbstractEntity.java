package pl.kuba.tau.domain;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
