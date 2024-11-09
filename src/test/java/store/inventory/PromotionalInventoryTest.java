package store.inventory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.product.Product;

public class PromotionalInventoryTest {

    private static final Product COKE = new Product("콜라", 1000, 10, "탄산2+1");
    private static final Product CIDER = new Product("사이다", 1000, 10, null);
    private static final PromotionalInventory PROMOTIONAL_INVENTORY = PromotionalInventory.PROMOTIONAL_INVENTORY;

    @BeforeEach
    void set_up() {
        List<Product> products = List.of(COKE, CIDER);
        PROMOTIONAL_INVENTORY.stackProducts(products);
        RegularInventory.REGULAR_INVENTORY.stackProducts(products);
    }

    @Test
    void 프로모션_상품만_관리() {
        assertThat(PROMOTIONAL_INVENTORY.findByName("콜라")).isNotNull();
        assertThat(PROMOTIONAL_INVENTORY.findByName("사이다")).isNull();
    }

    @Test
    void 상품명으로_상품_찾기() {
        assertThat(PROMOTIONAL_INVENTORY.findByName("콜라")).isEqualTo(COKE);
    }

    @Test
    void 프로모션_재고_차감() {
        PROMOTIONAL_INVENTORY.deduct("콜라", 5);
        assertThat(COKE).isEqualTo(new Product("콜라", 1_000, 5, "탄산2+1"));
    }

    @Test
    void 프로모션_재고에_없으면_일반_재고에서_차감() {
        PROMOTIONAL_INVENTORY.deduct("사이다", 5);
        assertThat(CIDER).isEqualTo(new Product("사이다", 1_000, 5, null));
    }
}
