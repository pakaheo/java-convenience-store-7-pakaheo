package store;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderDetailsParser {

    private static final Pattern PATTERN = Pattern.compile("\\[([가-힣]+)-(\\d+)]");

    public static Map<String, Integer> parse(String input) {
        Map<String, Integer> orders = new HashMap<>();

        Matcher matcher = PATTERN.matcher(input);
        while (matcher.find()) {
            orders.put(matcher.group(1), Integer.parseInt(matcher.group(2)));
        }

        return orders;
    }
}
