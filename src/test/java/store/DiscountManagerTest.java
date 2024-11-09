package store;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import store.product.Products;
import store.promotion.Promotions;

public class DiscountManagerTest {

    @Test
    void 총_구매_금액_계산() {
        OrderDetails orderDetails = new OrderDetails("[콜라-3],[사이다-4]");
        Products products = new Products(List.of("콜라,1000,10,탄산2+1", "사이다,1500,100,null"));

        DiscountManager discountManager = new DiscountManager(products, orderDetails);

        assertThat(discountManager.calculateTotal()).isEqualTo(9_000);
    }

    @Test
    void 프로모션_할인_금액_계산() {
        Promotions promotions = new Promotions(List.of("탄산2+1,2,1,2024-01-01,2024-12-31"));
        OrderDetails orderDetails = new OrderDetails("[콜라-3],[사이다-4]");
        Products products = new Products(List.of("콜라,1000,10,탄산2+1", "사이다,1500,100,null"));

        DiscountManager discountManager = new DiscountManager(products, orderDetails);

        //assertThat(discountManager.calculatePromotionDiscount()).isEqualTo(1_000);
    }
}
