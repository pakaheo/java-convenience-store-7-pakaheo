package store.product;

import constants.ErrorMessage;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import store.inventory.PromotionalInventory;
import store.inventory.RegularInventory;

public class Products {

    private static final String LINE_CHANGE = "\n";

    private final List<Product> productGroup;

    public Products(final List<String> productContents) {
        this.productGroup = ProductParser.parse(productContents);
        classify();
    }

    private void classify() {
        RegularInventory.REGULAR_INVENTORY.stackProducts(productGroup);
        PromotionalInventory.PROMOTIONAL_INVENTORY.stackProducts(productGroup);
    }

    public Product findByName(String productName) {
        return productGroup.stream()
                .filter(product -> product.hasName(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_EXISTS_PRODUCT.valueOf()));
    }

    public void checkQuantity(String productName, int purchaseCount) {
        if (isExceedQuantity(productName, purchaseCount)) {
            throw new IllegalArgumentException(ErrorMessage.EXCEED_QUANTITY.valueOf());
        }
    }

    private boolean isExceedQuantity(String productName, int purchaseCount) {
        return calculateActualQuantity(productName, purchaseCount) < purchaseCount;
    }

    private int calculateActualQuantity(String productName, int purchaseCount) {
        return productGroup.stream()
                .filter(product -> product.hasName(productName))
                .mapToInt(product -> product.currentQuantity(purchaseCount))
                .sum();
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
