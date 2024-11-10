package store;

import java.util.List;
import org.junit.jupiter.api.Test;
import store.discount.DiscountManager;
import store.order.OrderDetails;
import store.product.Products;

public class ReceiptTest {

    private static final Products PRODUCTS = new Products(List.of("콜라,1000,10,탄산2+1", "사이다,1500,5,null"));
    private static final OrderDetails ORDER_DETAILS = new OrderDetails("[콜라-5],[사이다-3]", PRODUCTS);
    private static final DiscountManager DISCOUNT_MANAGER = new DiscountManager(PRODUCTS);

    @Test
    void 영수증_출력() {
        Receipt receipt = new Receipt();

        System.out.println(receipt);
    }
}
