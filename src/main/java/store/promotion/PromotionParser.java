package store.promotion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PromotionParser {

    private static final String SEPARATE = ",";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<Promotion> parse(List<String> promotionContents) {
        List<Promotion> promotionGroup = new ArrayList<>();

        for (String content : promotionContents) {
            String[] splitted = splitBySeparate(content);
            promotionGroup.add(new Promotion(splitted[0], toInt(splitted[1]), toInt(splitted[2]), toDate(splitted[3]),
                    toDate(splitted[4])));
        }

        return promotionGroup;
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
