package jpa.jpa_shop.domain.orders;

import jpa.jpa_shop.domain.MiddleTable.OrderItem;
import jpa.jpa_shop.domain.delivery.Delivery;
import jpa.jpa_shop.domain.delivery.DeliveryStatus;
import jpa.jpa_shop.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bytecode.Throw;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Order Cancel

    //======= 관게 매핑 =========

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new LinkedList<>();

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;


    public void setMember(Member member)
    {
        this.member=member;
        member.getOrders().add(this);
    }

    public void addOrderItems(OrderItem orderItem)
    {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery)
    {
        this.delivery=delivery;
        delivery.setOrder(this);
    }

    public static Order createOrder(Member member,Delivery delivery,OrderItem... orderItems)
    {
        Order order=new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItems(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    // Business Logic
    public void cancel()
    {
        if(delivery.getStatus()== DeliveryStatus.COMPLETE)
        {
            throw new IllegalStateException("배송이 완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    public int getTotalPrice()
    {
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }


}
