package view;

import store.order.Receipt;
import store.product.Products;

public class OutputView {

    public void introduceProducts(Products products) {
        System.out.println(OutputMessage.WELCOME_MESSAGE.valueOf());
        System.out.println(OutputMessage.INTRODUCE_PRODUCTS_MESSAGE.valueOf());
        System.out.println(products);
        System.out.println(OutputMessage.INPUT_PRODUCT_NAME_AND_COUNT_MESSAGE.valueOf());
    }

    protected void introduceMoreProduct(String productName, int count) {
        System.out.printf(OutputMessage.MORE_FREE_PRODUCT_MESSAGE.valueOf(), productName, count);
    }

    protected void introduceLackPromotionStock(String productName, int count) {
        System.out.printf(OutputMessage.NOT_APPLY_PROMOTION_MESSAGE.valueOf(), productName, count);
    }

    protected void introduceMembership() {
        System.out.println(OutputMessage.MEMBERSHIP_DISCOUNT_MESSAGE.valueOf());
    }

    protected void introduceMorePurchase() {
        System.out.println(OutputMessage.PRODUCT_OTHER_PRODUCT_MESSAGE.valueOf());
    }

    public void showReceipt(Receipt receipt) {
        System.out.println(receipt);
    }
}
