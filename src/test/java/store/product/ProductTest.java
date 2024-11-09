package store.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    void 상품_생성() {
        Product coke = new Product("콜라", 1_000, 10, "탄산2+1");
        Product cider = new Product("사이다", 1_000, 10, null);

        assertThat(coke).isEqualTo(new Product("콜라", 1_000, 10, "탄산2+1"));
        assertThat(cider).isEqualTo(new Product("사이다", 1_000, 10, null));
    }

    @Test
    void 상품_정보_보여주기() {
        Product coke = new Product("콜라", 1_000, 10, "탄산2+1");
        Product cider = new Product("사이다", 1_000, 0, null);

        assertThat(coke.toString()).isEqualTo("- 콜라 1,000원 10개 탄산2+1");
        assertThat(cider.toString()).isEqualTo("- 사이다 1,000원 재고 없음");
    }
}
