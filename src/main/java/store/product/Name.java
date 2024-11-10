package store.product;

import java.util.Objects;

public class Name {

    private final String text;

    public Name(final String text) {
        this.text = text;
    }

    public boolean match(String productName) {
        return text.equals(productName);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Name name)) {
            return false;
        }
        return Objects.equals(text, name.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return text;
    }
}
