package store.product;

import java.util.ArrayList;
import java.util.List;

public class ProductParser {

    private static final String SEPARATE = ",";

    public static List<Product> parse(List<String> productContents) {
        List<Product> productGroup = new ArrayList<>();

        for (String content : productContents) {
            String[] splitted = splitBySeparate(content);
            productGroup.add(new Product(splitted[0], toInt(splitted[1]), toInt(splitted[2]), splitted[3]));
        }

        return productGroup;
    }

    private static String[] splitBySeparate(String content) {
        return content.split(SEPARATE);
    }

    private static int toInt(String text) {
        return Integer.parseInt(text);
    }
}
