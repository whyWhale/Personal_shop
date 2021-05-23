package jpa.jpa_shop.service.IFS;

import jpa.jpa_shop.domain.orders.Order;
import jpa.jpa_shop.web.controller.dto.request.order.OrderSearchRequestDto;

import java.util.List;

public interface OrderServiceIFS {

    public Long order(Long memberId,Long itemId,int count);

    public void cancelOrder(Long orderId);

    public List<Order> SearchMemberNameAndOrderStatus(OrderSearchRequestDto requestDto);
}
