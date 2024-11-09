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

    @Test
    void 상품_이름이_같은지_비교() {
        Product coke = new Product("콜라", 1_000, 10, "탄산2+1");

        assertThat(coke.hasName("콜라")).isTrue();
        assertThat(coke.hasName("코올라")).isFalse();
    }

    @Test
    void 상품_소계_계산() {
        Product coke = new Product("콜라", 1_000, 10, "탄산2+1");

        assertThat(coke.calculateSubTotal(3)).isEqualTo(3_000);
    }

    @Test
    void 상품에_프로모션이_적용되어있는지_확인() {
        Product coke = new Product("콜라", 1_000, 10, "탄산2+1");
        Product cider = new Product("사이다", 1_000, 0, null);

        assertThat(coke.isPromotional()).isTrue();
        assertThat(cider.isPromotional()).isFalse();
    }
}
