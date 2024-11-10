package store.option;

public class MorePurchaseOptionService implements OptionProvider {
    
    @Override
    public boolean meet() {
        return optionView.morePurchaseOption().equals(YES);
    }

    @Override
    public boolean meet(String productName, int rest) {
        return false;
    }
}
