package store;

import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        ConvenienceStore convenienceStore = new ConvenienceStore(new InputView(), new OutputView());
        convenienceStore.startPaymentSystem();
    }
}
