package store.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BuyNGetOneFreeTest {

    @Test
    void 생성() {
        BuyNGetOneFree buyNGetOneFree = new BuyNGetOneFree(2, 1);

        assertThat(buyNGetOneFree).isEqualTo(new BuyNGetOneFree(2, 1));
    }

    @Test
    void 상품_무료_증정_개수_계산() {
        BuyNGetOneFree twoPlusOne = new BuyNGetOneFree(2, 1);

        assertThat(twoPlusOne.freeCount()).isEqualTo(3);
    }

    @Test
    void 구입_개수와_무료_증정_개수_더하기() {
        BuyNGetOneFree twoPlusOne = new BuyNGetOneFree(2, 1);

        assertThat(twoPlusOne.sum()).isEqualTo(3);
    }
}
