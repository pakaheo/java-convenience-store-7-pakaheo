package store.inventory;

import java.util.List;
import store.product.Product;

public enum PromotionalInventory implements Inventory {

    PROMOTIONAL_INVENTORY;

    private List<Product> productGroup;

    @Override
    public void stackProducts(List<Product> productGroup) {
        this.productGroup = productGroup.stream().filter(Product::isPromotional).toList();
    }

    @Override
    public Product findByName(String productName) {
        return productGroup.stream().filter(product -> product.hasName(productName)).findFirst().orElse(null);
    }

    @Override
    public int deduct(String productName, int count) {
        Product product = findByName(productName);
        if (product == null) {
            RegularInventory.REGULAR_INVENTORY.deduct(productName, count);
            return 0;
        }

        int actualDecrease = product.deduct(count);
        changeRegularInventory(productName, count - actualDecrease);

        return actualDecrease;
    }

    private void changeRegularInventory(String productName, int rest) {
        if (rest > 0) {
            RegularInventory.REGULAR_INVENTORY.deduct(productName, rest);
        }
    }
}
