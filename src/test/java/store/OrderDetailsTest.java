package store;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class OrderDetailsTest {

    @Test
    void 주문내역_생성() {
        String input = "[콜라-3],[사이다-4]";

        assertThat(new OrderDetails(input)).isEqualTo(new OrderDetails(input));
    }
}
