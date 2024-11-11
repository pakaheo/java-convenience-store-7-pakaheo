package constants;

public enum ReceiptItems {

    PRODUCT_NAME("상품명"),
    QUANTITY("수량"),
    SUB_TOTAL("금액"),
    TOTAL("총구매액"),
    PROMOTION_DISCOUNT("행사할인"),
    MEMBERSHIP_DISCOUNT("멤버십할인"),
    MONEY_TO_PAY("내실돈"),
    MINUS("-");

    private final String text;

    ReceiptItems(final String text) {
        this.text = text;
    }

    public String valueOf() {
        return text;
    }
}
