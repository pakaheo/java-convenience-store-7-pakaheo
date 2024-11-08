package store.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ProductParserTest {

    @Test
    void 상품_리스트_생성() {
        List<String> productContents = List.of("콜라,1000,10,탄산2+1", "사이다,1000,10,null");

        assertThat(ProductParser.parse(productContents)).hasSize(2);
        assertThat(ProductParser.parse(productContents).getFirst()).isInstanceOf(Product.class);
    }
}
