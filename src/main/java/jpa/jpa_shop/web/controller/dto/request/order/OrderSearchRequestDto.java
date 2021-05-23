package jpa.jpa_shop.web.controller.dto.request.order;

import jpa.jpa_shop.domain.orders.OrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderSearchRequestDto {
    private String memberName;
    private OrderStatus orderStatus;

    @Builder
    private OrderSearchRequestDto(String memberName, OrderStatus orderStatus) {
        this.memberName = memberName;
        this.orderStatus = orderStatus;
    }


    public static OrderSearchRequestDto of(String memberName,OrderStatus orderStatus)
    {
        return OrderSearchRequestDto.builder()
                .memberName(memberName)
                .orderStatus(orderStatus)
                .build();
    }
}

