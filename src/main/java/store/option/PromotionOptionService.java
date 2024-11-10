package store;

public class PromotionOptionService implements OptionProvider {

    @Override
    public boolean noParameter() {
        return false;
    }

    @Override
    public boolean hasParameter(String productName, int rest) {
        return optionView.lackPromotionInventoryOption(productName, rest).equals(YES);
    }
}
