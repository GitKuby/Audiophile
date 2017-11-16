package pl.kuba.tau.domain;

public class Artist extends AbstractEntity {

    private String name;
    private int birthYear;

    public Artist(int id, String name, int birthYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
    }

    public Artist() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != getClass()) {
            return false;
        }
        Artist a = (Artist) o;
        return a.getId() == id && a.getName().equals(name) && a.birthYear == birthYear;
    }
}
