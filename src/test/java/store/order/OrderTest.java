package store.order;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import store.product.Products;
import store.promotion.Promotions;
import view.InputView;
import view.OptionView;
import view.OutputView;

public class OrderTest {

    private static final OrderDetails MEANINGLESS_ORDER_DETAILS = new OrderDetails("[콜라-5]",
            new Products(List.of("콜라,1000,10,null")));
    private static final OptionView OPTION = new OptionView(new InputView(), new OutputView());

    @BeforeEach
    void set_up() {
        Promotions promotions = new Promotions(
                List.of("탄산2+1,2,1,2024-01-01,2024-12-31", "MD추천상품,1,1,2024-01-01,2024-12-31"));
    }
}
