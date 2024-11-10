package store.option;

public class MoreProductOptionService implements OptionProvider {

    @Override
    public boolean meet() {
        return false;
    }

    @Override
    public boolean meet(String productName, int rest) {
        return optionView.moreProductOption(productName, rest).equals(YES);
    }
}
