package jpa.jpa_shop.web.dto.response.orderItem;

import lombok.Getter;

@Getter
public class OrderItemQueryDto {
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemQueryDto(String itemName, int orderPrice, int count) {
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
