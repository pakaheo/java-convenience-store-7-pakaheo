package store.product;

import constants.ErrorMessage;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import store.MoreProductOptionService;
import store.inventory.PromotionalInventory;
import store.inventory.RegularInventory;

public class Products {

    private static final String LINE_CHANGE = "\n";

    private final List<Product> productGroup;
    private final MoreProductOptionService moreProductOptionService = new MoreProductOptionService();

    public Products(List<String> productContents) {
        this.productGroup = ProductParser.parse(productContents);
        classify();
    }

    private void classify() {
        RegularInventory.REGULAR_INVENTORY.stackProducts(productGroup);
        PromotionalInventory.PROMOTIONAL_INVENTORY.stackProducts(productGroup);
    }

    public int deductInventory(String productName, int purchaseCount) {
        return PromotionalInventory.PROMOTIONAL_INVENTORY
                .deduct(productName, purchaseCount);
    }

    public int optimize(String productName, int purchaseCount) {
        int promotionCount = availablePromotionCount(productName);
        int rest = promotionCount - purchaseCount;

        if (rest > 0 && isNeedMoreProduct(productName, rest)) {
            purchaseCount = promotionCount;
        }
        return purchaseCount;
    }

    public int availablePromotionCount(String productName) {
        return findByName(productName).getPromotionEligibleCount();
    }

    private boolean isNeedMoreProduct(String productName, int rest) {
        return moreProductOptionService.hasParameter(productName, rest);
    }

    public Product findByName(String productName) {
        return productGroup.stream().filter(product -> product.hasName(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_EXISTS_PRODUCT.valueOf()));
    }

    public List<Product> getOrderedProduct(String productName) {
        return productGroup.stream().filter(product -> product.hasName(productName)).toList();
    }

    public boolean isPromotionProduct(Product product) {
        return product.isPromotional();
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
