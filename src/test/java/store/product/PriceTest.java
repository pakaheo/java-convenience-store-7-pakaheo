package store.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    void 가격_생성() {
        Price price = new Price(1_000);
        assertThat(price).isEqualTo(new Price(1_000));
    }

    @Test
    void 곱셈() {
        Price price = new Price(1_000);

        assertThat(price.multiply(3)).isEqualTo(3_000);
    }

    @Test
    void 숫자_세_자리마다_쉼표로_끊기() {
        Price price = new Price(1_000);
        
        assertThat(price.formalize()).isEqualTo("1,000");
    }
}
