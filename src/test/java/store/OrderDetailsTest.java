package store;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class OrderDetailsTest {

    @Test
    void 주문내역_생성() {
        String input = "[콜라-3],[사이다-4]";

        assertThat(new OrderDetails(input)).isEqualTo(new OrderDetails(input));
    }

    @Test
    void 주문내역_불러오기() {
        String input = "[콜라-3],[사이다-4]";
        Map<String, Integer> orders = new HashMap<>();
        orders.put("콜라", 3);
        orders.put("사이다", 4);

        assertThat(new OrderDetails(input).getOrders()).isEqualTo(orders);
    }
}
