package store;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.product.Products;
import store.promotion.Promotions;
import view.InputView;
import view.OptionView;
import view.OutputView;

public class OrderTest {

    private static final OrderDetails MEANINGLESS_ORDER_DETAILS = new OrderDetails("[콜라-5]");
    private static final OptionView OPTION = new OptionView(new InputView(), new OutputView());

    @BeforeEach
    void set_up() {
        Promotions promotions = new Promotions(
                List.of("탄산2+1,2,1,2024-01-01,2024-12-31", "MD추천상품,1,1,2024-01-01,2024-12-31"));
    }

    @Test
    void 총구매액_계산() {
        Products products = new Products(List.of("콜라,1000,10,탄산2+1", "사이다,1500,10,null"));
        Order order = new Order(products, MEANINGLESS_ORDER_DETAILS, OPTION);

        int total = order.calculateTotal("콜라", 5) + order.calculateTotal("사이다", 3);
        assertThat(total).isEqualTo(9_500);
    }

    @Test
    void 프로모션_할인_금액_계산() {
        Products products = new Products(List.of("콜라,1000,10,탄산2+1", "사이다,1000,10,MD추천상품"));
        Order order = new Order(products, MEANINGLESS_ORDER_DETAILS, OPTION);

        assertThat(order.calculatePromotionDiscount("콜라", 7)).isEqualTo(2_000);
        assertThat(order.calculatePromotionDiscount("사이다", 7)).isEqualTo(3_000);
    }

    @Test
    void 멤버십_할인_금액_계산() {
        Products products = new Products(List.of("콜라,1000,10,탄산2+1", "사이다,1000,10,null"));
        Order order = new Order(products, MEANINGLESS_ORDER_DETAILS, OPTION);

        assertThat(order.calculateMemberShipDiscount("사이다", 3)).isEqualTo(900);
    }
}
