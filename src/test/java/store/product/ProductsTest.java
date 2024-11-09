package store.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ProductsTest {

    @Test
    void 상품들_생성() {
        List<String> productGroup = List.of("콜라,1000,10,탄산2+1", "사이다,1000,100,null");

        assertThat(new Products(productGroup)).isEqualTo(new Products(productGroup));
    }

    @Test
    void 현재_상품_목록_보여주기() {
        List<String> productGroup = List.of("콜라,1000,10,탄산2+1", "사이다,1000,0,null");

        assertThat(new Products(productGroup).toString())
                .isEqualTo("- 콜라 1,000원 10개 탄산2+1\n- 사이다 1,000원 재고 없음");
    }
}
