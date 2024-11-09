package store;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderDetailsParser {

    private static final Pattern PATTERN = Pattern.compile("\\[([가-힣]+)-(\\d+)]");
    private static final int PRODUCT_NAME_PLACE = 1;
    private static final int PURCHASE_COUNT_PLACE = 2;

    public static Map<String, Integer> parse(String input) {
        Map<String, Integer> orders = new HashMap<>();
        Matcher matcher = createMatcher(input);

        while (canFind(matcher)) {
            orders.put(getProductName(matcher), getPurchaseCount(matcher));
        }

        return orders;
    }

    private static Matcher createMatcher(String input) {
        return PATTERN.matcher(input);
    }

    private static boolean canFind(Matcher matcher) {
        return matcher.find();
    }

    private static String getProductName(Matcher matcher) {
        return matcher.group(PRODUCT_NAME_PLACE);
    }

    private static int getPurchaseCount(Matcher matcher) {
        return toInt(matcher.group(PURCHASE_COUNT_PLACE));
    }

    private static int toInt(String text) {
        return Integer.parseInt(text);
    }
}
