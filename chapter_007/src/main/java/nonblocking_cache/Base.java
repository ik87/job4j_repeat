package nonblocking_cache;

import java.util.Objects;

public class Base {
    int id;
    int version;
    String name;

    public Base(int id, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return name.equals(base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
