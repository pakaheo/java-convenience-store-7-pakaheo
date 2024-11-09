package view;

import store.product.Products;

public class OutputView {

    public void introduceProducts(Products products) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\n");
        System.out.println(products);
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
    }

    protected void introduceMoreProduct(String productName, int count) {
        System.out.println("현재 " + productName + "은(는)" + count + "개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
    }

    protected void introduceLackPromotionStock(String productName, int count) {
        System.out.println("현재 " + productName + " " + count + "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
    }

    protected void introduceMembership() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
    }
}
