package constants;

public enum ReceiptFormat {

    PURCHASE_HISTORY_FORMAT("%-15s %-10s %-10s"),
    PROMOTION_HISTORY_FORMAT("%-15s %-10d"),
    FINAL_PAYMENT_FORMAT("%-15s %-10s");

    private final String text;

    ReceiptFormat(final String text) {
        this.text = text;
    }

    public String valueOf() {
        return text;
    }
}
