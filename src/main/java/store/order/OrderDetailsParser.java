package store.order;

import constants.ErrorMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderDetailsParser {

    private static final Pattern PATTERN = Pattern.compile("\\[([가-힣]+)-(\\d+)]");
    private static final int PRODUCT_NAME_PLACE = 1;
    private static final int PURCHASE_COUNT_PLACE = 2;
    private static final Character SEPARATOR = ',';

    public static Map<String, Integer> parse(String input) {
        checkEmpty(input);
        Map<String, Integer> orders = new HashMap<>();

        int lastMatch = addOrders(orders, input);
        checkInvalidInput(lastMatch, input.length());

        return orders;
    }

    private static void checkEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT_INPUT.valueOf());
        }
    }

    private static int addOrders(Map<String, Integer> orders, String input) {
        int lastMatch = 0;
        Matcher matcher = createMatcher(input);
        while (canFind(matcher)) {
            orders.put(getProductName(matcher), getPurchaseCount(matcher));
            lastMatch = matcher.end();
            checkSeparate(lastMatch, input);
        }
        return lastMatch;
    }

    private static void checkSeparate(int lastMatch, String input) {
        if (lastMatch < input.length() && input.charAt(lastMatch) != SEPARATOR) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT_INPUT.valueOf());
        }
    }

    private static void checkInvalidInput(int lastMatch, int inputLength) {
        if (lastMatch != inputLength) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT_INPUT.valueOf());
        }
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
