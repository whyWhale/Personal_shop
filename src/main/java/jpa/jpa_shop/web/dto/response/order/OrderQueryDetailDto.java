package jpa.jpa_shop.web.dto.response.order;

import jpa.jpa_shop.domain.member.Address;
import jpa.jpa_shop.domain.orders.OrderStatus;
import jpa.jpa_shop.web.dto.response.orderItem.OrderItemQueryDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderQueryDetailDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemQueryDto> orderItems;



    public OrderQueryDetailDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }

    public void orderItemsAdd(List<OrderItemQueryDto> orderItemQueryDto)
    {
        orderItems=new ArrayList<>(orderItemQueryDto);
    }
}
