package jpa.jpa_shop.web.dto.response.order;

import jpa.jpa_shop.domain.member.Address;
import jpa.jpa_shop.domain.orders.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class OrderResponseDto {
    private Long id;
    private String itemRepresentation;
    private int totalPrice;
    private OrderStatus orderStatus;
    private String memberName;
    private Address address;
    private LocalDateTime orderDate;


    @Builder
    public OrderResponseDto(Long id, String itemRepresentation,int totalPrice,LocalDateTime orderDate, OrderStatus orderStatus, String memberName, Address address) {
        this.id = id;
        this.itemRepresentation=itemRepresentation;
        this.totalPrice=totalPrice;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.memberName = memberName;
        this.address = address;
    }
}
