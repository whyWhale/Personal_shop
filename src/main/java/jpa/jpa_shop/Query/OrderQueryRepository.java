package jpa.jpa_shop.Query;

import jpa.jpa_shop.web.dto.response.order.OrderFlatDto;
import jpa.jpa_shop.web.dto.response.order.OrderQueryDetailDto;
import jpa.jpa_shop.web.dto.response.order.OrderQueryDto;
import jpa.jpa_shop.web.dto.response.orderItem.OrderItemQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class OrderQueryRepository {
    private final EntityManager em;
    // N+1의 문제 발생. : 3개 (1+2<컬렉션>)
    public List<OrderQueryDetailDto> findDetailList(int offset, int limit) {
        List<OrderQueryDetailDto> orders = findOrderDetails(offset, limit);
        orders.forEach(orderQueryDto -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(orderQueryDto.getOrderId());
            orderQueryDto.orderItemsAdd(orderItems);
        });
        return orders;
    }
    // N+1의 문제 해결(In keyword) : 2개 (1+1<컬렉션>)
    public List<OrderQueryDetailDto> ImproveFindDetailList(int offset, int limit)
    {
        List<OrderQueryDetailDto> orders=findOrderDetails(offset,limit);
        List<Long> orderIds = orders.stream().map(OrderQueryDetailDto::getOrderId).collect(Collectors.toList());
        List<OrderItemQueryDto> orderItems = OrderItemInQueryDtos(orderIds);

        // map Stream
        Map<Long, List<OrderItemQueryDto>> orderItemGrouping = memoryMap(orderItems);
        orders.forEach(orderQueryDetailDto ->orderQueryDetailDto.orderItemsAdd(orderItemGrouping.get(orderQueryDetailDto.getOrderId())));
        return orders;
    }

    private Map<Long, List<OrderItemQueryDto>> memoryMap(List<OrderItemQueryDto> orderItems) {
        return orderItems.stream().collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
    }

    private List<OrderItemQueryDto> OrderItemInQueryDtos(List<Long> orderIds) {
        return em.createQuery(
                "select  new jpa.jpa_shop.web.dto.response.orderItem.OrderItemQueryDto(oi.order.id,i.name,oi.orderPrice,oi.count)" +
                        " from OrderItem oi" +
                        " join oi.item i" +
                        " where oi.order.id in :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();
    }

    // flatData
    public List<OrderFlatDto> findAll_flatData()
    {
        return em.createQuery("select new jpa.jpa_shop.web.dto.response.order.OrderFlatDto(" +
                "o.id, m.name, o.orderDate,o.status, d.address, i.name, oi.orderPrice, oi.count) from Order o " +
                " join o.member m join o.delivery d" +
                " join o.orderItems oi join oi.item i",OrderFlatDto.class)
                .getResultList();
    }

    public List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery(
                "select  new jpa.jpa_shop.web.dto.response.orderItem.OrderItemQueryDto(oi.order.id,i.name,oi.orderPrice,oi.count)" +
                        " from OrderItem oi" +
                        " join oi.item i" +
                        " where oi.order.id = : orderId"
                , OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }


    public List<OrderQueryDto> findOrders(int offset, int limit) {
        return em.createQuery(
                "select new jpa.jpa_shop.web.dto.response.order.OrderQueryDto(o.id,m.name,o.orderDate,o.status, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", OrderQueryDto.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    private List<OrderQueryDetailDto> findOrderDetails(int offset, int limit) {
        return em.createQuery(
                "select new jpa.jpa_shop.web.dto.response.order.OrderQueryDetailDto(o.id,m.name,o.orderDate,o.status, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", OrderQueryDetailDto.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
