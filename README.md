# java-convenience-store-precourse

## 기능 구현 목록

### 입력

- 구현에 필요한 상품 목록을 파일 입출력을 통해 불러온다. **(Done)**
- 구현에 필요한 행사 목록을 파일 입출력을 통해 불러온다. **(Done)**
- 구매할 상품과 수량을 입력 받는다. ex) [콜라-3],[사이다-4] **(Done)**
- 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부를 입력받는다.
    - Y: 증정 받을 수 있는 상품을 추가한다.
    - N: 증정 받을 수 있는 상품을 추가하지 않는다.
- 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부를 입력받는다.
    - Y: 일부 수량에 대해 정가로 결제한다.
    - N: 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다.
- 멤버십 할인 적용 여부를 입력 받는다.
    - Y: 멤버십 할인을 적용한다.
    - N: 멤버십 할인을 적용하지 않는다.
- 추가 구매 여부를 입력 받는다.
    - Y: 재고가 업데이트된 상품 목록을 확인 후 추가로 구매를 진행한다.
    - N: 구매를 종료한다.

### 출력

- 환영 인사와 함께 상품명, 가격, 프로모션 이름, 재고를 안내한다. 만약 재고가 0개라면 재고 없음을 출력한다. **(Done)**
- 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우, 혜택에 대한 안내 메시지를 출력한다.
- 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부에 대한 안내 메시지를 출력한다.
- 멤버십 할인 적용 여부를 확인하기 위해 안내 문구를 출력한다.
- 영수증을 출력한다.
- 추가 구매 여부를 확인하기 위해 안내 문구를 출력한다.

### 상품

- 상품 입력들을 Parsing하여 상품 리스트를 만든다. **(Done)**
- 상품 리스트로 Products 객체를 생성한다. **(Done)**
- 상품은 상품명, 가격, 수량, 프로모션 이름을 가진다. **(Done)**
- 상품은 상품명, 가격, 수량, 프로모션 상태를 보여줄 수 있어야 한다. 이때 수량이 없으면 '재고 없음'으로 표현한다. **(Done)**

### 프로모션

- 행사 입력들을 Parsing하여 프로모션 리스트를 만든다. **(Done)**
- 프로모션 리스트로 Promotions 객체를 생성한다. **(Done)**
- 프로모션은 Buy N Get 1 형태이며, 1+1과 2+1이 있다.
- 프로모션은 프로모션 이름, 구매 수량, 무료로 얻는 수량, 시작일자, 종료일자로 구성한다. **(Done)**

## 주문내역

- 사용자의 입력에서 구매를 희망하는 상품과 수량을 추출한다. **(Done)**
- 추출한 정보를 바탕으로 주문 내역을 생성한다. **(Done)**
