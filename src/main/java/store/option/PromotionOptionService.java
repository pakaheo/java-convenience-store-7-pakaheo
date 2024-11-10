package store.option;

public class PromotionOptionService implements OptionProvider {

    @Override
    public boolean meet() {
        return false;
    }

    @Override
    public boolean meet(String productName, int rest) {
        return optionView.lackPromotionInventoryOption(productName, rest).equals(YES);
    }
}
