package store;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

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
        System.out.println("상품명\t수량\t금액");
        purchaseItems.forEach(System.out::println);

        System.out.println("============= 증정 ==============");
        freeItems.forEach(System.out::println);

        System.out.println("====================================");
        System.out.println("총구매액\t\t" + totalAmount);
        System.out.println("행사할인\t\t-" + promotionDiscount);
        System.out.println("멤버십할인\t-" + membershipDiscount);
        System.out.println("내실돈\t\t" + (totalAmount - promotionDiscount - membershipDiscount));
    }
}
