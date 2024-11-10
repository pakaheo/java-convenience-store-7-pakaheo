package store;

import controller.ConvenienceStore;
import store.option.MorePurchaseOptionService;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] xargs) {
        ConvenienceStore convenienceStore = new ConvenienceStore(new InputView(), new OutputView(),
                new MorePurchaseOptionService());
        convenienceStore.startPaymentSystem();
    }
}
