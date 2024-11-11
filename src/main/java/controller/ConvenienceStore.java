package controller;

import store.discount.DiscountManager;
import store.option.MembershipOptionService;
import store.option.MoreProductOptionService;
import store.option.MorePurchaseOptionService;
import store.order.Order;
import store.order.OrderDetails;
import store.order.Receipt;
import store.product.Products;
import view.InputView;
import view.OutputView;

public class ConvenienceStore {

    private final InputView input;
    private final OutputView output;
    private final MorePurchaseOptionService morePurchaseOptionService;
    private final MembershipOptionService membershipOptionService;

    public ConvenienceStore(final InputView input, final OutputView output,
                            final MorePurchaseOptionService morePurchaseOptionService,
                            final MembershipOptionService membershipOptionService) {
        this.input = input;
        this.output = output;
        this.morePurchaseOptionService = morePurchaseOptionService;
        this.membershipOptionService = membershipOptionService;
    }

    public void startPaymentSystem() {
        input.inputPromotions();
        Products products = input.inputProducts();
        do {
            output.introduceProducts(products);
            OrderDetails orderDetails = input.inputProductAndQuantity(products);
            Order order = new Order(orderDetails, products, new DiscountManager(products),
                    new MoreProductOptionService());
            Receipt receipt = order.progress(membershipOptionService.meet());
            receipt.print();
        } while (morePurchaseOptionService.meet());
    }
}
