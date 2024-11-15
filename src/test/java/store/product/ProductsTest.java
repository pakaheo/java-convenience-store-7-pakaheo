package store.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import constants.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.inventory.PromotionalInventory;
import store.promotion.Promotions;

public class ProductsTest {

    @BeforeEach
    void set_up() {
        Promotions promotions = new Promotions(
                List.of("탄산2+1,2,1,2024-01-01,2024-12-31", "MD추천상품,1,1,2024-01-01,2024-12-31"));
    }

    @Test
    void 상품들_생성() {
        List<String> productGroup = List.of("콜라,1000,10,탄산2+1", "사이다,1000,100,null");

        assertThat(new Products(productGroup)).isEqualTo(new Products(productGroup));
    }

    @Test
    void 현재_상품_목록_보여주기() {
        List<String> productGroup = List.of("콜라,1000,10,탄산2+1", "사이다,1000,0,null");

        assertThat(new Products(productGroup).toString())
                .isEqualTo("- 콜라 1,000원 10개 탄산2+1\n- 사이다 1,000원 재고 없음");
    }

    @Test
    void 프로모션_재고에서_차감() {
        Products products = new Products(List.of("콜라,1000,10,탄산2+1"));
        products.deductInventory("콜라", 5);

        Product coke = PromotionalInventory.PROMOTIONAL_INVENTORY.findByName("콜라");

        assertThat(coke.toString()).isEqualTo("- 콜라 1,000원 5개 탄산2+1");
    }

    @Test
    void 존재하지_않는_상품이면_예외() {
        Products products = new Products(List.of("콜라,1000,10,탄산2+1"));
        assertThatThrownBy(() -> products.findByName("사이다"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_EXISTS_PRODUCT.valueOf());
    }
}
