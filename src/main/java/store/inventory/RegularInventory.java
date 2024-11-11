package store.inventory;

import java.util.List;
import store.product.Product;

public enum RegularInventory implements Inventory {

    REGULAR_INVENTORY;

    private List<Product> productGroup;

    @Override
    public void stackProducts(List<Product> productGroup) {
        this.productGroup = productGroup.stream().filter(product -> !product.isPromotional()).toList();
    }

    @Override
    public Product findByName(String productName) {
        return productGroup.stream().filter(product -> product.hasName(productName)).findFirst().orElse(null);
    }

    @Override
    public void deduct(String productName, int count) {
        findByName(productName).deduct(count);
    }
}
