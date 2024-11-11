package store.inventory;

import camp.nextstep.edu.missionutils.DateTimes;
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
    public void deduct(String productName, int count) {
        findByName(productName).deduct(count);
    }

    public int calculatePromotionDiscount(String productName, int purchaseCount) {
        Product product = findByName(productName);
        if (isNotPromotional(product)) {
            return 0;
        }

        int freeCount = product.calculateFreeCount();
        return freeCount * product.getPrice();
    }

    private boolean isNotPromotional(Product product) {
        return product == null || !product.isPromotional() || !product.isProgressingPromotion(
                DateTimes.now().toLocalDate());
    }
}
