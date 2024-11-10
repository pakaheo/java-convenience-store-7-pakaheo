package store;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,###");

    private final List<String> purchaseItems = new ArrayList<>();
    private final List<String> freeItems = new ArrayList<>();
    private int totalAmount;
    private int promotionDiscount;
    private int membershipDiscount;

    public void addItem(String name, int quantity, int price) {
        purchaseItems.add(name + "\t" + quantity + "\t" + price);
        totalAmount += price * quantity;
    }

    public void addFreeItem(String name, int quantity) {
        freeItems.add(name + "\t" + quantity);
    }

    public void applyPromotionDiscount(int discount) {
        promotionDiscount += discount;
    }

    public void applyMembershipDiscount(int discount) {
        membershipDiscount += discount;
    }

    public void print() {
        System.out.println("============== W 편의점 ================");
        System.out.printf("%-15s %-10s %-10s\n", "상품명", "수량", "금액");
        for (String item : purchaseItems) {
            String[] parts = item.split("\t");
            String name = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            int price = Integer.parseInt(parts[2]);
            System.out.printf("%-15s %-10d %-10s\n", name, quantity, PRICE_FORMAT.format(price));
        }

        System.out.println("============= 증정 ==============");

        // 증정 항목 출력
        for (String item : freeItems) {
            String[] parts = item.split("\t");
            String name = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            System.out.printf("%-15s %-10d\n", name, quantity);
        }

        System.out.println("====================================");
        System.out.printf("%-15s %-10s\n", "총구매액", PRICE_FORMAT.format(totalAmount));
        System.out.printf("%-15s %-10s\n", "행사할인", "-" + PRICE_FORMAT.format(promotionDiscount));
        System.out.printf("%-15s %-10s\n", "멤버십할인", "-" + PRICE_FORMAT.format(membershipDiscount));
        System.out.printf("%-15s %-10s\n", "내실돈",
                PRICE_FORMAT.format((totalAmount - promotionDiscount - membershipDiscount)));
    }
}
