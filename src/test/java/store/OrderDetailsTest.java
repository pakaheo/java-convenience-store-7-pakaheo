package store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import constants.ErrorMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import store.product.Products;

public class OrderDetailsTest {

    private static final Products PRODUCTS = new Products(List.of("콜라,1000,10,null"));

    @Test
    void 주문내역_생성() {
        String input = "[콜라-3]";

        assertThat(new OrderDetails(input, PRODUCTS)).isEqualTo(new OrderDetails(input, PRODUCTS));
    }

    @Test
    void 주문내역_불러오기() {
        String input = "[콜라-3]";
        Map<String, Integer> orders = new HashMap<>();
        orders.put("콜라", 3);

        assertThat(new OrderDetails(input, PRODUCTS).getOrders()).isEqualTo(orders);
    }

    @Test
    void 존재하지_않는_상품이면_예외() {
        Products products = new Products(List.of("콜라,1000,10,null"));

        assertThatThrownBy(() -> new OrderDetails("[사이다-5]", PRODUCTS))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_EXISTS_PRODUCT.valueOf());
    }

    @Test
    void 구매_희망_수량이_재고_수량보다_많으면_예외() {
        assertThatThrownBy(() -> new OrderDetails("[콜라-11]", PRODUCTS))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.EXCEED_QUANTITY.valueOf());
    }
}
