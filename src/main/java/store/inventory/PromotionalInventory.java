package store.inventory;

import java.util.List;
import store.option.PromotionOptionService;
import store.product.Product;

public enum PromotionalInventory implements Inventory {

    PROMOTIONAL_INVENTORY;

    private List<Product> productGroup;
    private final PromotionOptionService promotionOptionService = new PromotionOptionService();

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

        return processPromotionDeduction(product, count);
    }

    private int processPromotionDeduction(Product product, int count) {
        int actualDecrease = product.deduct(count);
        int nonPromotionCount = count % product.getPromotionEligibleCount();
        handleRegularInventory(product, count - actualDecrease, nonPromotionCount);

        return actualDecrease;
    }

    private void handleRegularInventory(Product product, int remainingCount, int nonPromotionCount) {
        if (remainingCount > 0 && promotionOptionService.meet(product.getName(), remainingCount)) {
            RegularInventory.REGULAR_INVENTORY.deduct(product.getName(), remainingCount - nonPromotionCount);
        }
    }
}
