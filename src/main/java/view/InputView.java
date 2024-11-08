package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import store.Products;

public class InputView {

    private static final String PRODUCTS_FILE_PATH = "src/main/resources/products.md";
    private static final String NOT_FOUND_FILE = "파일을 읽어들일 수 없습니다.";

    public Products inputProducts() {
        List<String> productContents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCTS_FILE_PATH))) {
            String productContent;
            reader.readLine();
            while ((productContent = reader.readLine()) != null) {
                productContents.add(productContent);
            }
        } catch (IOException ioException) {
            throw new IllegalArgumentException(NOT_FOUND_FILE);
        }

        return new Products(productContents);
    }
}
