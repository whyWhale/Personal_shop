package jpa.jpa_shop.web.dto.response.order;

import jpa.jpa_shop.domain.MiddleTable.OrderItem;
import jpa.jpa_shop.domain.member.Address;
import jpa.jpa_shop.domain.orders.Order;
import jpa.jpa_shop.domain.orders.OrderStatus;
import jpa.jpa_shop.web.dto.response.orderItem.OrderItemResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderDetailResponseDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemResponseDto> orderItems;

    @Builder
    public OrderDetailResponseDto(Order order) {
        this.orderId = order.getId();
        this.name = order.getMember().getName();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.address = order.getDelivery().getAddress();
        this.orderItems = order.getOrderItems().stream().map(OrderItem::toDto).collect(Collectors.toList());
    }
}
