package view;

import camp.nextstep.edu.missionutils.Console;
import constants.ErrorMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import store.order.OrderDetails;
import store.product.Products;
import store.promotion.Promotions;

public class InputView {

    private static final String PRODUCTS_FILE_PATH = "src/main/resources/products.md";
    private static final String PROMOTIONS_FILE_PATH = "src/main/resources/promotions.md";
    private static final String NOT_FOUND_FILE = "파일을 읽어들일 수 없습니다.";
    private static final String YES = "Y";
    private static final String NO = "N";

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

    public OrderDetails inputProductAndQuantity(Products products) {
        OrderDetails orderDetails = null;
        try {
            orderDetails = new OrderDetails(Console.readLine(), products);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            inputProductAndQuantity(products);
        }
        return orderDetails;
    }

    protected String inputAnswer() {
        String answer;
        while (true) {
            answer = Console.readLine();
            if (isValidInput(answer)) {
                break;
            }
            System.out.println(ErrorMessage.WRONG_INPUT.valueOf());
        }
        return answer;
    }

    private boolean isValidInput(String answer) {
        return answer.equals(YES) || answer.equals(NO);
    }
}
