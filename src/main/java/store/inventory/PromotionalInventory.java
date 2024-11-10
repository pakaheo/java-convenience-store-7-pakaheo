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
        this.productGroup = productGroup.stream().filter(product -> product.isPromotional()).toList();
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

        return calculateActualDecrease(product, productName, count);
    }

    private int calculateActualDecrease(Product product, String productName, int count) {
//        int actualDecrease = product.deduct(count);
//        int nonPromotionCount = count % product.getPromotionEligibleCount();
//        changeRegularInventory(productName, count - actualDecrease, nonPromotionCount);
//
//        return actualDecrease;
        int promotionEligibleCount = product.getPromotionEligibleCount();
        int totalPromotionSets = count / promotionEligibleCount;
        int actualPromotionItems = totalPromotionSets * promotionEligibleCount;

        // 프로모션 재고에서 가능한 만큼 차감
        int actualDecrease = product.compareQuantity(actualPromotionItems);
        product.deduct(actualDecrease);

        // 프로모션으로 제공할 수 없는 수량 계산
        int remainingCount = count - actualDecrease;
        int nonPromotionCount = remainingCount % promotionEligibleCount;

        changeRegularInventory(productName, remainingCount, nonPromotionCount);

        return actualDecrease;
    }

    private void changeRegularInventory(String productName, int remainingCount, int nonPromotionCount) {
        if (remainingCount > 0 && promotionOptionService.hasParameter(productName, remainingCount)) {
            RegularInventory.REGULAR_INVENTORY.deduct(productName, remainingCount - nonPromotionCount);
        }
    }
}
