package store.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import constants.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.Test;
import store.inventory.PromotionalInventory;
import store.inventory.RegularInventory;

public class ProductsTest {

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
    void 상품명으로_상품_가져오기() {
        List<String> productGroup = List.of("콜라,1000,10,탄산2+1", "사이다,1000,0,null");

        assertThat(new Products(productGroup).findByName("콜라"))
                .isEqualTo(new Product("콜라", 1_000, 10, "탄산2+1"));
    }

    @Test
    void 존재하지_않는_상품이면_예외() {
        List<String> productGroup = List.of("콜라,1000,10,탄산2+1", "사이다,1000,0,null");

        assertThatThrownBy(() -> new Products(productGroup).findByName("음료수"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_EXISTS_PRODUCT.valueOf());
    }

    @Test
    void 구매_희망_수량이_재고_수량보다_많으면_예외() {
        List<String> productGroup = List.of("콜라,1000,8,탄산2+1", "콜라,1000,2,null");

        assertThatThrownBy(() -> new Products(productGroup).checkQuantity("콜라", 11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.EXCEED_QUANTITY.valueOf());
    }

    @Test
    void 일반재고와_프로모션재고로_분류하기() {
        List<String> productGroup = List.of("콜라,1000,10,탄산2+1", "사이다,1000,0,null");
        Products products = new Products(productGroup);

        products.classify();

        assertThat(RegularInventory.REGULAR_INVENTORY.getProductGroup()).isNotNull();
        assertThat(PromotionalInventory.PROMOTIONAL_INVENTORY.getProductGroup()).isNotNull();
    }
}
