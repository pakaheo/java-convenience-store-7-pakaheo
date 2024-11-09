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
    public void deduct(String productName, int count) {

    }

    @Override
    public List<Product> getProductGroup() {
        return productGroup;
    }
}
