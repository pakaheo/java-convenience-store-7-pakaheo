package store.discount;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.product.Product;
import store.product.Products;
import store.promotion.Promotions;

public class DiscountManagerTest {

    private static final String COKE_INFORMATION = "콜라,1000,10,탄산2+1";
    private static final String CIDER_INFORMATION = "사이다,1000,5,null";

    @BeforeEach
    void 프로모션_초기화() {
        Promotions promotions = new Promotions(List.of("탄산2+1,2,1,2024-01-01,2024-12-31"));
    }

    @Test
    void 프로모션_할인_금액_계산() {
        Products products = new Products(List.of(COKE_INFORMATION, CIDER_INFORMATION));
        assertThat(new DiscountManager(products).calculatePromotionDiscount("콜라", 3)).isEqualTo(1_000);
    }

    @Test
    void 멤버십_할인_금액_계산() {
        Products products = new Products(List.of(COKE_INFORMATION, CIDER_INFORMATION));
        Map<Product, Integer> orders = new HashMap<>();
        orders.put(new Product("사이다", 1_000, 5, null), 5);

        assertThat(new DiscountManager(products).calculateMemberShipDiscount(orders)).isEqualTo(1_500);
    }
}
