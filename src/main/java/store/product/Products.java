package store.product;

import java.util.List;
import java.util.Objects;

public class Products {

    private final List<Product> productGroup;

    public Products(final List<String> productContents) {
        this.productGroup = ProductParser.parse(productContents);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Products products)) {
            return false;
        }
        return Objects.equals(productGroup, products.productGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroup);
    }
}
