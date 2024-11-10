package store.product;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import store.inventory.PromotionalInventory;
import store.inventory.RegularInventory;
import view.InputView;
import view.OptionView;
import view.OutputView;

public class Products {

    private static final String LINE_CHANGE = "\n";

    private final List<Product> productGroup;
    private OptionView option = new OptionView(new InputView(), new OutputView());

    public Products(List<String> productContents) {
        this.productGroup = ProductParser.parse(productContents);
        classify();
    }

    private void classify() {
        RegularInventory.REGULAR_INVENTORY.stackProducts(productGroup);
        PromotionalInventory.PROMOTIONAL_INVENTORY.stackProducts(productGroup);
    }

    public int deductInventory(String productName, int purchaseCount) {
        // 상품을 구한다
        Product product = productGroup.stream().filter(pro -> pro.hasName(productName)).toList().getFirst();
        int canPromotionCount = product.neededCount();
        if (purchaseCount < canPromotionCount) {
            if (option.moreProductOption(productName, canPromotionCount - purchaseCount).equals("Y")) {
                purchaseCount = canPromotionCount;
            }
        }

        return PromotionalInventory.PROMOTIONAL_INVENTORY.deduct(productName, purchaseCount);
    }

    public boolean isExceedQuantity(String productName, int purchaseCount) {
        return calculateActualQuantity(productName, purchaseCount) < purchaseCount;
    }

    private int calculateActualQuantity(String productName, int purchaseCount) {
        return getOrderedProduct(productName)
                .mapToInt(product -> product.currentQuantity(purchaseCount))
                .sum();
    }

    public Stream<Product> getOrderedProduct(String productName) {
        return productGroup.stream().filter(product -> product.hasName(productName)).toList().stream();
    }

    public boolean isPromotionProduct(String productName) {
        return productGroup.stream().filter(product -> product.hasName(productName))
                .anyMatch(Product::isPromotional);
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
