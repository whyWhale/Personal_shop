package jpa.jpa_shop.service;

import jpa.jpa_shop.domain.MiddleTable.OrderItem;
import jpa.jpa_shop.domain.delivery.Delivery;
import jpa.jpa_shop.domain.delivery.DeliveryStatus;
import jpa.jpa_shop.domain.item.Item;
import jpa.jpa_shop.domain.item.Repository.ItemRepository;
import jpa.jpa_shop.domain.member.Member;
import jpa.jpa_shop.domain.member.Repository.MemberRepository;
import jpa.jpa_shop.domain.orders.Order;
import jpa.jpa_shop.domain.orders.Repository.OrderRepository;
import jpa.jpa_shop.web.controller.dto.request.OrderSearchRequestDto;
import jpa.jpa_shop.service.IFS.OrderServiceIFS;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService implements OrderServiceIFS {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    @Transactional
    @Override
    public Long order(Long memberId, Long itemId, int count) {
        Member member=memberRepository.findById(memberId);
        Item item = itemRepository.findById(itemId);

        Delivery delivery= Delivery.builder()
                .address(member.getAddress())
                .status(DeliveryStatus.READY)
                .build();


        OrderItem orderItem= OrderItem.createOrderItem(item,item.getPrice(),count);
        Order order=Order.createOrder(member,delivery,orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    @Override
    public void cancelOrder(Long orderId) {
        Order order=orderRepository.findById(orderId);
        order.cancel();
    }

    @Override
    public List<Order> SearchMemberNameAndOrderStatus(OrderSearchRequestDto requestDto) {
        return orderRepository.findAll(requestDto);
    }


}
