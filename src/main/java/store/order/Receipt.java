package store.order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import store.order.constants.ReceiptFormat;
import store.order.constants.ReceiptHeader;
import store.order.constants.ReceiptItems;

public class Receipt {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,###");
    private static final String TAB = "\t";
    private static final String LINE_CHANGE = "\n";


    private final List<String> purchaseItems = new ArrayList<>();
    private final List<String> freeItems = new ArrayList<>();
    private int totalAmount;
    private int promotionDiscount;
    private int membershipDiscount;

    public void addItem(String name, int quantity, int price) {
        purchaseItems.add(name + TAB + quantity + TAB + price);
        totalAmount += price * quantity;
    }

    public void addFreeItem(String name, int quantity) {
        freeItems.add(name + TAB + quantity);
    }

    public void applyPromotionDiscount(int discount) {
        promotionDiscount += discount;
    }

    public void applyMembershipDiscount(int discount) {
        membershipDiscount += discount;
    }

    public void print() {
        welcome();
        purchaseHistory();
        promotionHistory();
        finalPayment();
    }

    private void welcome() {
        System.out.println(ReceiptHeader.STORE_HEADER.valueOf());
    }

    private void purchaseHistory() {
        System.out.printf(ReceiptFormat.PURCHASE_HISTORY_FORMAT.valueOf() + LINE_CHANGE,
                ReceiptItems.PRODUCT_NAME.valueOf(), ReceiptItems.QUANTITY.valueOf(), ReceiptItems.SUB_TOTAL.valueOf());

        for (String item : purchaseItems) {
            String[] parts = item.split(TAB);
            System.out.printf(ReceiptFormat.PURCHASE_HISTORY_FORMAT.valueOf() + LINE_CHANGE, parts[0], toInt(parts[1]),
                    PRICE_FORMAT.format((long) toInt(parts[2]) * toInt(parts[1])));
        }
    }

    private void promotionHistory() {
        System.out.println(ReceiptHeader.PROMOTION_HEADER.valueOf());

        for (String item : freeItems) {
            String[] parts = item.split(TAB);
            System.out.printf(ReceiptFormat.PROMOTION_HISTORY_FORMAT.valueOf() + LINE_CHANGE, parts[0],
                    toInt(parts[1]));
        }
    }

    private int toInt(String text) {
        return Integer.parseInt(text);
    }

    private void finalPayment() {
        System.out.println(ReceiptHeader.FINAL_PAYMENT_HEADER.valueOf());
        finalTotal();
        finalPromotionDiscount();
        finalMembershipDiscount();
        finalMoneyToPay();
    }

    private void finalTotal() {
        System.out.printf(ReceiptFormat.FINAL_PAYMENT_FORMAT.valueOf() + LINE_CHANGE, ReceiptItems.TOTAL.valueOf(),
                PRICE_FORMAT.format(totalAmount));
    }

    private void finalPromotionDiscount() {
        System.out.printf(ReceiptFormat.FINAL_PAYMENT_FORMAT.valueOf() + LINE_CHANGE,
                ReceiptItems.PROMOTION_DISCOUNT.valueOf(),
                ReceiptItems.MINUS.valueOf() + PRICE_FORMAT.format(promotionDiscount));
    }

    private void finalMembershipDiscount() {
        System.out.printf(ReceiptFormat.FINAL_PAYMENT_FORMAT.valueOf() + LINE_CHANGE,
                ReceiptItems.MEMBERSHIP_DISCOUNT.valueOf(),
                ReceiptItems.MINUS.valueOf() + PRICE_FORMAT.format(membershipDiscount));
    }

    private void finalMoneyToPay() {
        System.out.printf(ReceiptFormat.FINAL_PAYMENT_FORMAT.valueOf() + LINE_CHANGE,
                ReceiptItems.MONEY_TO_PAY.valueOf(),
                PRICE_FORMAT.format((totalAmount - promotionDiscount - membershipDiscount)));
    }
}
