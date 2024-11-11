package view;

public enum OutputMessage {

    WELCOME_MESSAGE("안녕하세요. W편의점입니다."),
    INTRODUCE_PRODUCTS_MESSAGE("현재 보유하고 있는 상품입니다.\n"),
    INPUT_PRODUCT_NAME_AND_COUNT_MESSAGE("\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    MORE_FREE_PRODUCT_MESSAGE("\n현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n"),
    NOT_APPLY_PROMOTION_MESSAGE("\n현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n"),
    MEMBERSHIP_DISCOUNT_MESSAGE("\n멤버십 할인을 받으시겠습니까? (Y/N)"),
    PRODUCT_OTHER_PRODUCT_MESSAGE("\n감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");

    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }

    public String valueOf() {
        return message;
    }
}
