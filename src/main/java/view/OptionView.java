package view;

public class OptionView {

    private final InputView input;
    private final OutputView output;

    public OptionView(InputView input, OutputView output) {
        this.input = input;
        this.output = output;
    }

    public String moreProductCase(String productName, int count) {
        output.introduceMoreProduct(productName, count);
        return input.inputAnswer();
    }

    public String lackPromotionStockCase(String productName, int count) {
        output.introduceLackPromotionStock(productName, count);
        return input.inputAnswer();
    }

    public String membershipCase() {
        output.introduceMembership();
        return input.inputAnswer();
    }
}
