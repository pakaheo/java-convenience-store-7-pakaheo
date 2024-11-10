# java-convenience-store-precourse

## <기능 구현 목록>

### [입력]

-[X] 구현에 필요한 상품 목록을 파일 입출력을 통해 불러온다. **(Done)**
-[X] 구현에 필요한 행사 목록을 파일 입출력을 통해 불러온다. **(Done)**
-[X] 구매할 상품과 수량을 입력 받는다. ex) [콜라-3],[사이다-4] **(Done)**

-[X] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부를 입력받는다. **(Done)**
    - 1+1에서 1개를 가져온 경우, 2+1에서 1개를 가져온 경우, 2+1에서 2개를 가져온 경우
    - Y: 증정 받을 수 있는 상품을 추가한다.
    - N: 증정 받을 수 있는 상품을 추가하지 않는다.

-[X] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부를 입력받는다. **(Done)**
    - Y: 일부 수량에 대해 정가로 결제한다.
    - N: 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다.

-[X] 멤버십 할인 적용 여부를 입력 받는다. **(Done)**
    - Y: 멤버십 할인을 적용한다.
    - N: 멤버십 할인을 적용하지 않는다.

-[X] 추가 구매 여부를 입력 받는다.
    - Y: 재고가 업데이트된 상품 목록을 확인 후 추가로 구매를 진행한다.
    - N: 구매를 종료한다.

### [출력]

-[X] 환영 인사와 함께 상품명, 가격, 프로모션 이름, 재고를 안내한다. 만약 재고가 0개라면 재고 없음을 출력한다. **(Done)**
-[X] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우, 혜택에 대한 안내 메시지를 출력한다. **(Done)**
-[X] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부에 대한 안내 메시지를 출력한다. **(Done)**
-[X] 멤버십 할인 적용 여부를 확인하기 위해 안내 문구를 출력한다. **(Done)**

-[X] 영수증을 출력한다.
    - 구매 상품 내역: 구매한 상품명, 수량, 가격
    - 증정 상품 내역: 프로모션에 따라 무료로 제공된 증정 상품의 목록
    - 금액 정보
        - 총구매액: 구매한 상품의 총 수량과 총 금액
        - 행사할인: 프로모션에 의해 할인된 금액
        - 멤버십할인: 멤버십에 의해 추가로 할인된 금액
        - 내실돈: 최종 결제 금액

-[X] 추가 구매 여부를 확인하기 위해 안내 문구를 출력한다. **(Done)**

### [상품]

-[X] 상품 입력들을 Parsing하여 상품 리스트를 만든다. **(Done)**
-[X] 상품 리스트로 Products 객체를 생성한다. **(Done)**
-[X] 상품은 상품명, 가격, 수량, 프로모션 이름을 가진다. **(Done)**
-[X] 상품은 상품명, 가격, 수량, 프로모션 상태를 보여줄 수 있어야 한다. 이때 수량이 없으면 '재고 없음'으로 표현한다. **(Done)**

### [프로모션]

-[X] 행사 입력들을 Parsing하여 프로모션 리스트를 만든다. **(Done)**
-[X] 프로모션 리스트로 Promotions 객체를 생성한다. **(Done)**
-[X] 프로모션은 프로모션 이름, 구매 수량, 무료로 얻는 수량, 시작일자, 종료일자로 구성한다. **(Done)**
-[X] 프로모션은 프로모션 기간 내일 때만 적용할 수 있다.

## [주문내역]

-[X] 사용자의 입력에서 구매를 희망하는 상품과 수량을 추출한다. **(Done)**
-[X] 추출한 정보를 바탕으로 주문 내역을 생성한다. **(Done)**

## [최종 결제 금액 계산]

-[X] 총 구매 금액 계산 **(Done)**
    - 상품별로 가격 * 수량 합산 **(Success)**

-[X] 프로모션 할인 금액 계산 **(Done)**
    - 고객이 구매하려는 상품이 프로모션 상품인지 확인 **(Success)**
    - 프로모션이 적용되어 무료로 제공된 상품의 수 * 상품의 가격 계산 **(Success)**

-[X] 멤버십 할인 금액 계산
    - 적용 대상 : 프로모션이 적용되지 않은 상품 (일반 재고에만 있는 상품) **(Success)**
    - 프로모션 미적용 금액의 30% 할인 **(Success)**
    - 할인 최대 한도 = 8,000원 **(Success)**

## [재고]

-[X] 상품들은 일반 상품과 프로모션 상품으로 구분할 수 있다. **(Done)**
-[X] 구매 희망 상품이 재고에 존재하지 않으면 예외 처리한다. **(Done)**
    - [ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.
-[X] 상품 구매 희망 개수가 해당 상품의 재고보다 많으면 예외 처리한다 **(Done)**
    - [ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.
-[X] 상품 구매 시 프로모션 상품이 아니라면 일반 재고에서 구매 개수만큼 차감한다. **(Done)**
-[X] 상품 구매 시 프로모션 상품이라면 프로모션 재고에서 구매 개수만큼 차감한다. **(Done)**
    - 프로모션 재고가 부족하다면 일반 재고에서 나머지 개수를 차감한다.
