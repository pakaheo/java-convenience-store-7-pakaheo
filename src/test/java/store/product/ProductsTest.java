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
    void 존재하지_않는_상품이면_예외() {
        List<String> productGroup = List.of("콜라,1000,10,탄산2+1", "사이다,1000,0,null");

        assertThatThrownBy(() -> new Products(productGroup).checkExistProduct("음료수"))
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
    void 프로모션_재고에서_차감() {
        Products products = new Products(List.of("콜라,1000,10,탄산2+1"));
        products.deductInventory("콜라", 5);

        Product coke = PromotionalInventory.PROMOTIONAL_INVENTORY.findByName("콜라");

        assertThat(coke.toString()).isEqualTo("- 콜라 1,000원 5개 탄산2+1");
    }

    @Test
    void 일반_재고에서_차감() {
        Products products = new Products(List.of("콜라,1000,10,null"));
        products.deductInventory("콜라", 5);

        Product coke = RegularInventory.REGULAR_INVENTORY.findByName("콜라");

        assertThat(coke.toString()).isEqualTo("- 콜라 1,000원 5개");
    }

    @Test
    void 프로모션_재고가_부족하면_일반_재고에서_차감() {
        Products products = new Products(List.of("콜라,1000,10,탄산2+1", "콜라,1000,10,null"));
        products.deductInventory("콜라", 12);

        Product promotionCoke = PromotionalInventory.PROMOTIONAL_INVENTORY.findByName("콜라");
        Product regularCoke = RegularInventory.REGULAR_INVENTORY.findByName("콜라");

        assertThat(promotionCoke.toString()).isEqualTo("- 콜라 1,000원 재고 없음 탄산2+1");
        assertThat(regularCoke.toString()).isEqualTo("- 콜라 1,000원 8개");
    }
}
