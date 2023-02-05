# 재고 주문 관리

### 프로젝트 간단 소개

상품 재고 현황을 편리하게 파악하고 상품을 주문할 수 있으며 재고 소진 여부 를 확인할 수 있습니다. 


### Tech Stack

**BackEnd**: **Java11**, **SpringBoot**, Thymeleaf, **JPA**, **QueryDSL**, JUnit4

**DevOps**:  **MySQL** ,**Redis**, Travis, **AWS**


### 개발 프로세스

- **Builder 패턴 지향하여 가독성**을 높인다.
- **DTO 객체를 통해 컴포넌트간 메시지**를 주고받는다.
- **불변성 유지**를 위해 **Getter 는 열어두고, Setter 는 지향**한다.
- **정적 팩토리 메서드(Static Factory Method) 이용하여 가독성**을 높인다.
- **양방향 관계를 지양하여 의존성을 떨어뜨려 변경에 대한 스코프**를 줄인다.
- **도메인 모델 패턴 활용하여 객체지향적 개발을 진행**한다.
- **표준 스펙을 최대한 이용**한다.

### ERD

<img width="500" alt="스크린샷 2023-02-05 오후 2 09 51" src="https://user-images.githubusercontent.com/67587446/216802875-acf73d9e-b947-4eb2-bfb5-9de566617688.png">


### System Design

<img width="500" alt="스크린샷 2023-02-05 오후 2 09 28" src="https://user-images.githubusercontent.com/67587446/216802864-20ad9e17-5751-4546-a802-8af675ebc72d.png">
