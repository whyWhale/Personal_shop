package jpa.jpa_shop.web.controller.dto.response.orderItem;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderItemResponseDto {
    private String itemName;
    private int orderPrice;
    private int count;

    @Builder
    public OrderItemResponseDto(String itemName, int orderPrice, int count) {
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
