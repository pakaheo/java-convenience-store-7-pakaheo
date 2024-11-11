package store.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class QuantityTest {

    @Test
    void 수량_생성() {
        Quantity quantity = new Quantity(10);
        assertThat(quantity).isEqualTo(new Quantity(10));
    }

    @Test
    void 최솟값_구하기() {
        Quantity quantity = new Quantity(10);
        assertThat(quantity.calculateMinimum(9)).isEqualTo(9);
    }

    @Test
    void 수량_줄이기() {
        Quantity quantity = new Quantity(10);
        assertThat(quantity.reduce(5)).isEqualTo(new Quantity(5));
    }

    @ParameterizedTest
    @CsvSource(value = {"10:10개", "0:재고 없음"}, delimiter = ':')
    void to_string(int number, String expected) {
        assertThat(new Quantity(number).toString()).isEqualTo(expected);
    }
}
