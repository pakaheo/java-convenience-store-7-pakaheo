package store;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class OrderDetailsParserTest {

    @Test
    void 주문내역_생성() {
        String input = "[콜라-3],[사이다-4]";

        assertThat(OrderDetailsParser.parse(input)).hasSize(2);
    }
}
