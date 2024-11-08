package store;

import java.util.Map;
import java.util.Objects;

public class OrderDetails {

    private final Map<String, Integer> orders;

    public OrderDetails(String input) {
        this.orders = OrderDetailsParser.parse(input);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof OrderDetails that)) {
            return false;
        }
        return Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }
}
