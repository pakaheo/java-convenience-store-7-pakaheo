package store.inventory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.product.Product;

public class RegularInventoryTest {

    private static final Product COKE = new Product("콜라", 1000, 10, "탄산2+1");
    private static final Product CIDER = new Product("사이다", 1000, 10, null);
    private static final RegularInventory REGULAR_INVENTORY = RegularInventory.REGULAR_INVENTORY;

    @BeforeEach
    void set_up() {
        List<Product> products = List.of(COKE, CIDER);
        REGULAR_INVENTORY.stackProducts(products);
    }

    @Test
    void 일반_상품만_관리() {
        assertThat(REGULAR_INVENTORY.findByName("콜라")).isNull();
        assertThat(REGULAR_INVENTORY.findByName("사이다")).isNotNull();
    }

    @Test
    void 상품명으로_상품_찾기() {
        assertThat(REGULAR_INVENTORY.findByName("사이다")).isEqualTo(CIDER);
    }

    @Test
    void 일반_재고_차감() {
        REGULAR_INVENTORY.deduct("사이다", 5);
        assertThat(CIDER).isEqualTo(new Product("사이다", 1_000, 5, null));
    }
}
