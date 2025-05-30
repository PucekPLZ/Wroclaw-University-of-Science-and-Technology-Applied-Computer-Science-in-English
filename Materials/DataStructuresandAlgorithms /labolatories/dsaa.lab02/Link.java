package dsaa.lab02;

import java.util.Objects;

public class Link{
    public String ref;
    public Link(String ref) {
        this.ref=ref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return ref.equals(link.ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ref);
    }
    // in the future there will be more fields
}
