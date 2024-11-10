package store.option;

public class MoreProductOptionService implements OptionProvider {

    @Override
    public boolean noParameter() {
        return false;
    }

    @Override
    public boolean hasParameter(String productName, int rest) {
        return optionView.moreProductOption(productName, rest).equals(YES);
    }
}
