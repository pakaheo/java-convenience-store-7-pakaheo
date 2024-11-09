package view;

import camp.nextstep.edu.missionutils.Console;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import store.OrderDetails;
import store.product.Products;
import store.promotion.Promotions;

public class InputView {

    private static final String PRODUCTS_FILE_PATH = "src/main/resources/products.md";
    private static final String PROMOTIONS_FILE_PATH = "src/main/resources/promotions.md";
    private static final String NOT_FOUND_FILE = "파일을 읽어들일 수 없습니다.";

    public Products inputProducts() {
        List<String> productContents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCTS_FILE_PATH))) {
            String productContent;
            reader.readLine();
            while ((productContent = reader.readLine()) != null) {
                productContents.add(productContent);
            }
        } catch (IOException ioException) {
            throw new IllegalArgumentException(NOT_FOUND_FILE);
        }

        return new Products(productContents);
    }

    public Promotions inputPromotions() {
        List<String> promotionContents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PROMOTIONS_FILE_PATH))) {
            String promotionContent;
            reader.readLine();
            while ((promotionContent = reader.readLine()) != null) {
                promotionContents.add(promotionContent);
            }
        } catch (IOException ioException) {
            throw new IllegalArgumentException(NOT_FOUND_FILE);
        }

        return new Promotions(promotionContents);
    }

    public OrderDetails inputProductAndQuantity() {
        return new OrderDetails(Console.readLine());
    }

    public String inputAnswer() {
        return Console.readLine();
    }
}
