package store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import constants.ErrorMessage;
import org.junit.jupiter.api.Test;

public class OrderDetailsParserTest {

    @Test
    void 주문내역_생성() {
        String input = "[콜라-3],[사이다-4]";

        assertThat(OrderDetailsParser.parse(input)).hasSize(2);
    }

    @Test
    void 잘못된_주문내역_입력() {
        String input = "[콜라3],[사이다-4)";

        assertThatThrownBy(() -> OrderDetailsParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_INPUT.valueOf());
    }
}
