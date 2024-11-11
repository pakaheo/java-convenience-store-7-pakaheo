package view;

import camp.nextstep.edu.missionutils.Console;
import constants.ErrorMessage;
import store.order.OrderDetails;
import store.product.Products;
import store.promotion.Promotions;

public class InputView {

    private static final String PRODUCTS_FILE_PATH = "src/main/resources/products.md";
    private static final String PROMOTIONS_FILE_PATH = "src/main/resources/promotions.md";
    private static final String NOT_FOUND_FILE = "파일을 읽어들일 수 없습니다.";
    private static final String YES = "Y";
    private static final String NO = "N";

    private final ResourceReader resourceReader;

    public InputView() {
        resourceReader = new ResourceReader();
    }

    public Products inputProducts() {
        return new Products(resourceReader.read(PRODUCTS_FILE_PATH));
    }

    public Promotions inputPromotions() {
        return new Promotions(resourceReader.read(PROMOTIONS_FILE_PATH));
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
