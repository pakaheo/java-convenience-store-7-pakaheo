package constants;

public enum ErrorMessage {

    NOT_EXISTS_PRODUCT("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요."),
    EXCEED_QUANTITY("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String valueOf() {
        return message;
    }
}
