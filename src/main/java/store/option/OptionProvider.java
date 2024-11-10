package store.option;

import view.InputView;
import view.OptionView;
import view.OutputView;

public interface OptionProvider {

    String YES = "Y";
    OptionView optionView = new OptionView(new InputView(), new OutputView());

    boolean meet();

    boolean meet(String productName, int rest);
}
