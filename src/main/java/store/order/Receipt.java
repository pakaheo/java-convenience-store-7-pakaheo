package store.order;

import java.text.DecimalFormat;
import java.util.Map;
import store.order.constants.ReceiptFormat;
import store.order.constants.ReceiptHeader;
import store.order.constants.ReceiptItems;
import store.product.Product;

public class Receipt {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,###");
    private static final String TAB = "\t";
    private static final String LINE_CHANGE = "\n";


    private final Map<Product, Integer> purchaseItems;
    private final Map<Product, Integer> freeItems;
    private final int totalAmount;
    private final int promotionDiscount;
    private final int membershipDiscount;

    public Receipt(Map<Product, Integer> purchaseItems, Map<Product, Integer> freeItems, int totalAmount,
                   int promotionDiscount, int membershipDiscount) {
        this.purchaseItems = purchaseItems;
        this.freeItems = freeItems;
        this.totalAmount = totalAmount;
        this.promotionDiscount = promotionDiscount;
        this.membershipDiscount = membershipDiscount;
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

        for (Map.Entry<Product, Integer> entry : purchaseItems.entrySet()) {
            System.out.printf(ReceiptFormat.PURCHASE_HISTORY_FORMAT.valueOf() + LINE_CHANGE, entry.getKey().getName(),
                    entry.getValue(),
                    PRICE_FORMAT.format((long) entry.getKey().getPrice() * entry.getValue()));
        }
    }

    private void promotionHistory() {
        System.out.println(ReceiptHeader.PROMOTION_HEADER.valueOf());

        for (Map.Entry<Product, Integer> entry : freeItems.entrySet()) {
            System.out.printf(ReceiptFormat.PROMOTION_HISTORY_FORMAT.valueOf() + LINE_CHANGE, entry.getKey().getName(),
                    entry.getValue());
        }
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
