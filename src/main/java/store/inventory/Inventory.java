package store.inventory;

import java.util.List;
import store.product.Product;

public interface Inventory {

    void stackProducts(List<Product> productGroup);

    void deduct(String productName, int count);

    List<Product> getProductGroup();
}
