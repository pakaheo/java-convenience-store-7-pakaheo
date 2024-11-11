package store.order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,###");
    private static final String TAB = "\t";
    private static final String LINE_CHANGE = "\n";
    private static final String STORE_HEADER = "============== W 편의점 ================";
    private static final String PURCHASE_HISTORY_FORMAT = "%-15s %-10s %-10s";
    private static final String PRODUCT_NAME = "상품명";
    private static final String QUANTITY = "수량";
    private static final String SUB_TOTAL = "금액";
    private static final String PROMOTION_HEADER = "============== 증   정 ================";
    private static final String PROMOTION_HISTORY_FORMAT = "%-15s %-10d";
    private static final String FINAL_PAYMENT_HEADER = "====================================";
    private static final String FINAL_PAYMENT_FORMAT = "%-15s %-10s";
    private static final String TOTAL = "총구매액";
    private static final String PROMOTION_DISCOUNT = "행사할인";
    private static final String MEMBERSHIP_DISCOUNT = "멤버십할인";
    private static final String MONEY_TO_PAY = "내실돈";
    private static final String MINUS = "-";

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
        System.out.println(STORE_HEADER);
    }

    private void purchaseHistory() {
        System.out.printf(PURCHASE_HISTORY_FORMAT + LINE_CHANGE, PRODUCT_NAME, QUANTITY, SUB_TOTAL);
        
        for (String item : purchaseItems) {
            String[] parts = item.split(TAB);
            System.out.printf(PURCHASE_HISTORY_FORMAT + LINE_CHANGE, parts[0], toInt(parts[1]),
                    PRICE_FORMAT.format((long) toInt(parts[2]) * toInt(parts[1])));
        }
    }

    private void promotionHistory() {
        System.out.println(PROMOTION_HEADER);

        for (String item : freeItems) {
            String[] parts = item.split(TAB);
            System.out.printf(PROMOTION_HISTORY_FORMAT + LINE_CHANGE, parts[0], toInt(parts[1]));
        }
    }

    private int toInt(String text) {
        return Integer.parseInt(text);
    }

    private void finalPayment() {
        System.out.println(FINAL_PAYMENT_HEADER);
        finalTotal();
        finalPromotionDiscount();
        finalMembershipDiscount();
        finalMoneyToPay();
    }

    private void finalTotal() {
        System.out.printf(FINAL_PAYMENT_FORMAT + LINE_CHANGE, TOTAL, PRICE_FORMAT.format(totalAmount));
    }

    private void finalPromotionDiscount() {
        System.out.printf(FINAL_PAYMENT_FORMAT + LINE_CHANGE, PROMOTION_DISCOUNT,
                MINUS + PRICE_FORMAT.format(promotionDiscount));
    }

    private void finalMembershipDiscount() {
        System.out.printf(FINAL_PAYMENT_FORMAT + LINE_CHANGE, MEMBERSHIP_DISCOUNT,
                MINUS + PRICE_FORMAT.format(membershipDiscount));
    }

    private void finalMoneyToPay() {
        System.out.printf(FINAL_PAYMENT_FORMAT + LINE_CHANGE, MONEY_TO_PAY,
                PRICE_FORMAT.format((totalAmount - promotionDiscount - membershipDiscount)));
    }
}
