package controller;

import store.discount.DiscountManager;
import store.option.MembershipOptionService;
import store.option.MorePurchaseOptionService;
import store.order.Order;
import store.order.OrderDetails;
import store.product.Products;
import view.InputView;
import view.OutputView;

public class ConvenienceStore {

    private InputView input;
    private OutputView output;
    private MorePurchaseOptionService morePurchaseOptionService;

    public ConvenienceStore(InputView input, OutputView output, MorePurchaseOptionService morePurchaseOptionService) {
        this.input = input;
        this.output = output;
        this.morePurchaseOptionService = morePurchaseOptionService;
    }

    public void startPaymentSystem() {
        input.inputPromotions();
        Products products = input.inputProducts();
        do {
            output.introduceProducts(products);
            OrderDetails orderDetails = input.inputProductAndQuantity(products);
            Order order = new Order(products, orderDetails, new DiscountManager(products),
                    new MembershipOptionService());
            order.progress();
        } while (morePurchaseOptionService.meet());
    }
}
