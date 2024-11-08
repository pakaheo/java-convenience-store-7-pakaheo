package store.product;

import java.util.Objects;

public class Product {

    private final String name;
    private final int price;
    private int quantity;
    private final String promotionName;

    public Product(final String name, final int price, int quantity, final String promotionName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Product product)) {
            return false;
        }
        return price == product.price && quantity == product.quantity && Objects.equals(name, product.name)
                && Objects.equals(promotionName, product.promotionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, promotionName);
    }
}
