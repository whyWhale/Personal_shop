package jpa.jpa_shop.Query;

import jpa.jpa_shop.web.dto.response.order.OrderQueryDetailDto;
import jpa.jpa_shop.web.dto.response.order.OrderQueryDto;
import jpa.jpa_shop.web.dto.response.orderItem.OrderItemQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderQueryRepository {
    private final EntityManager em;

    public List<OrderQueryDetailDto> findDetailList(int offest, int limit) {
        List<OrderQueryDetailDto> orders = findOrderDetails(offest, limit);
        orders.forEach(orderQueryDto -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(orderQueryDto.getOrderId());
            orderQueryDto.orderItemsAdd(orderItems);
        });
        return orders;
    }

    public List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery(
                "select  new jpa.jpa_shop.web.dto.response.orderItem.OrderItemQueryDto(i.name,oi.orderPrice,oi.count)" +
                        " from OrderItem oi" +
                        " join oi.item i" +
                        " where oi.order.id = : orderId"
                , OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }


    public List<OrderQueryDto> findOrders(int offest, int limit) {
        return em.createQuery(
                "select new jpa.jpa_shop.web.dto.response.order.OrderQueryDto(o.id,m.name,o.orderDate,o.status, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", OrderQueryDto.class)
                .setFirstResult(offest)
                .setMaxResults(limit)
                .getResultList();
    }

    private List<OrderQueryDetailDto> findOrderDetails(int offest, int limit) {
        return em.createQuery(
                "select new jpa.jpa_shop.web.dto.response.order.OrderQueryDetailDto(o.id,m.name,o.orderDate,o.status, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", OrderQueryDetailDto.class)
                .setFirstResult(offest)
                .setMaxResults(limit)
                .getResultList();
    }
}
