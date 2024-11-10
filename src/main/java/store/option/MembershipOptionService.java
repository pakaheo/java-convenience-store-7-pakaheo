package store.option;

public class MembershipOptionService implements OptionProvider {

    @Override
    public boolean meet() {
        return optionView.membershipOption().equals(YES);
    }

    @Override
    public boolean meet(String productName, int rest) {
        return false;
    }
}
