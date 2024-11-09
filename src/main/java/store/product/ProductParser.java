package store.product;

import java.util.ArrayList;
import java.util.List;
import store.promotion.Promotion;
import store.promotion.PromotionParser;

public class ProductParser {

    private static final String SEPARATE = ",";
    private static final String TEXT_NULL = "null";

    public static List<Product> parse(List<String> productContents) {
        List<Product> productGroup = new ArrayList<>();

        for (String content : productContents) {
            String[] splitted = splitBySeparate(content);
            productGroup.add(new Product(splitted[0], toInt(splitted[1]), toInt(splitted[2]), checkNull(splitted[3])));
        }

        return productGroup;
    }

    private static String[] splitBySeparate(String content) {
        return content.split(SEPARATE);
    }

    private static int toInt(String text) {
        return Integer.parseInt(text);
    }

    private static Promotion checkNull(String text) {
        if (text.equals(TEXT_NULL)) {
            return null;
        }
        return PromotionParser.getPromotion(text);
    }
}
