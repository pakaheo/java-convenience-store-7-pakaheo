package store;

public class MemberOptionService implements OptionProvider {

    @Override
    public boolean noParameter() {
        return optionView.membershipOption().equals(YES);
    }

    @Override
    public boolean hasParameter(String productName, int rest) {
        return false;
    }
}
