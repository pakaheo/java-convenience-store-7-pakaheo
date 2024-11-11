package store.inventory;

import java.util.List;
import store.product.Product;

public interface Inventory {

    void stackProducts(List<Product> productGroup);

    Product findByName(String productName);

    void deduct(String productName, int count);
}
