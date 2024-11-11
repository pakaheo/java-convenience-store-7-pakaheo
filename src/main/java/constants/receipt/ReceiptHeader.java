package constants;

public enum ReceiptHeader {
    
    STORE_HEADER("============== W 편의점 ================"),
    PROMOTION_HEADER("============== 증   정 ================"),
    FINAL_PAYMENT_HEADER("======================================");

    private final String text;

    ReceiptHeader(final String text) {
        this.text = text;
    }

    public String valueOf() {
        return text;
    }
}
