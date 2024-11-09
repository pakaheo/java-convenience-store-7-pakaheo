package store.product;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Products {

    private static final String LINE_CHANGE = "\n";

    private final List<Product> productGroup;

    public Products(final List<String> productContents) {
        this.productGroup = ProductParser.parse(productContents);
    }

    public Product findByName(String productName) {
        return productGroup.stream().filter(product -> product.hasName(productName)).findFirst().orElse(null);
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

    @Override
    public String toString() {
        return productGroup.stream().map(Product::toString).collect(Collectors.joining(LINE_CHANGE));
    }
}
