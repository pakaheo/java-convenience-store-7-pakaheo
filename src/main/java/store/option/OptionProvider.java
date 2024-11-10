package store;

import view.InputView;
import view.OptionView;
import view.OutputView;

public interface OptionProvider {

    String YES = "Y";
    OptionView optionView = new OptionView(new InputView(), new OutputView());

    boolean noParameter();

    boolean hasParameter(String productName, int rest);
}
