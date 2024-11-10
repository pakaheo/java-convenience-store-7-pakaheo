package controller;

import store.discount.DiscountManager;
import store.option.MemberOptionService;
import store.order.Order;
import store.order.OrderDetails;
import store.product.Products;
import view.InputView;
import view.OptionView;
import view.OutputView;

public class ConvenienceStore {

    private InputView input;
    private OutputView output;
    private OptionView option;

    public ConvenienceStore(InputView input, OutputView output) {
        this.input = input;
        this.output = output;
        this.option = new OptionView(input, output);
    }

    public void startPaymentSystem() {
        // 상품 및 프로모션 입력
        input.inputPromotions();
        Products products = input.inputProducts();

        do {
            // 환영 인사 및 재고 안내
            output.introduceProducts(products);
            // 구매 상품 및 수량 입력
            OrderDetails orderDetails = input.inputProductAndQuantity(products);
            Order order = new Order(products, orderDetails, new DiscountManager(products), new MemberOptionService());

            order.progress();
        } while (option.morePurchaseOption().equals("Y"));
    }
}
