package store;

import controller.ConvenienceStore;
import store.option.MorePurchaseOptionService;
import view.InputView;
import view.OutputView;
import view.ResourceReader;

public class Application {

    public static void main(String[] xargs) {
        ConvenienceStore convenienceStore = new ConvenienceStore(new InputView(new ResourceReader()), new OutputView(),
                new MorePurchaseOptionService());
        convenienceStore.startPaymentSystem();
    }
}
