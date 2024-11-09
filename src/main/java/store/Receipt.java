package store;

public class Receipt {

    private static final String SEPARATE_LINE_SIGN = "=";
    private static final String LINE_CHANGE = "\n";

    private final OrderDetails orderDetails;
    private final PromotionDetails promotionDetails;

    public Receipt(final OrderDetails orderDetails, final PromotionDetails promotionDetails) {
        this.orderDetails = orderDetails;
        this.promotionDetails = promotionDetails;
    }

    @Override
    public String toString() {
        String result = "";

        result += SEPARATE_LINE_SIGN.repeat(14) + "W 편의점" + SEPARATE_LINE_SIGN.repeat(14) + LINE_CHANGE;
        result += orderDetails.toString();
        result += SEPARATE_LINE_SIGN.repeat(13) + "증\t정" + SEPARATE_LINE_SIGN.repeat(13) + LINE_CHANGE;
        result += promotionDetails;
        result += SEPARATE_LINE_SIGN.repeat(36);

        return result;
    }
}
