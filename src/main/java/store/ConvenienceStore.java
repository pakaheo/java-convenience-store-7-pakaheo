package store;

import store.product.Products;
import store.promotion.Promotions;
import view.InputView;
import view.OptionView;
import view.OutputView;

public class ConvenienceStore {

    private InputView input;
    private OutputView output;
    private OptionView option;

    public ConvenienceStore(InputView input, OutputView output, OptionView option) {
        this.input = input;
        this.output = output;
        this.option = option;
    }

    public void startPaymentSystem() {
        // 상품 및 프로모션 입력
        Promotions promotions = input.inputPromotions();
        Products products = input.inputProducts();
        // 환영 인사 및 재고 안내
        output.introduceProducts(products);
        // 구매 상품 및 수량 입력
        OrderDetails orderDetails = input.inputProductAndQuantity();
        Order order = new Order(products, orderDetails, option);
    }
}
