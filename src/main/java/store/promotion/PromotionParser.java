package store.promotion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionParser {

    private static final String SEPARATE = ",";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Map<String, Promotion> PROMOTIONS = new HashMap<>();

    public static List<Promotion> parse(List<String> promotionContents) {
        List<Promotion> promotionGroup = new ArrayList<>();

        for (String content : promotionContents) {
            String[] splitted = splitBySeparate(content);
            Promotion promotion = new Promotion(splitted[0], toInt(splitted[1]), toInt(splitted[2]),
                    toDate(splitted[3]), toDate(splitted[4]));
            promotionGroup.add(promotion);
            PROMOTIONS.put(splitted[0], promotion);
        }
        return promotionGroup;
    }

    public static Promotion getPromotion(String name) {
        return PROMOTIONS.get(name);
    }

    private static String[] splitBySeparate(String content) {
        return content.split(SEPARATE);
    }

    private static int toInt(String text) {
        return Integer.parseInt(text);
    }

    private static LocalDate toDate(String date) {
        return LocalDate.parse(date, DATE_FORMAT);
    }
}
