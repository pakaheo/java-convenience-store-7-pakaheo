package store.inventory;

import java.util.List;
import store.product.Product;
import view.InputView;
import view.OptionView;
import view.OutputView;

public enum PromotionalInventory implements Inventory {

    PROMOTIONAL_INVENTORY;

    private List<Product> productGroup;
    private final OptionView option = new OptionView(new InputView(), new OutputView());

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

        return calculateActualDecrease(product, productName, count);
    }

    private int calculateActualDecrease(Product product, String productName, int count) {
        int actualDecrease = product.deduct(count);
        changeRegularInventory(productName, count - actualDecrease);

        return actualDecrease;
    }

    private void changeRegularInventory(String productName, int rest) {
        if (rest > 0 && option.lackPromotionStockOption(productName, rest).equals("Y")) {
            RegularInventory.REGULAR_INVENTORY.deduct(productName, rest);
        }
    }
}
