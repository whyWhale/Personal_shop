package jpa.jpa_shop.web.dto.response.order;

import jpa.jpa_shop.domain.member.Address;
import jpa.jpa_shop.domain.orders.OrderStatus;
import jpa.jpa_shop.web.dto.response.orderItem.OrderItemQueryDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderFlatDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    private String itemName;
    private int orderPrice;
    private int count;


    public OrderFlatDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
