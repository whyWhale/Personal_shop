package jpa.jpa_shop.domain.orders.Repository;

import jpa.jpa_shop.domain.orders.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order)
    {
        em.persist(order);
    }

    public Order findById(Long id)
    {
        return em.find(Order.class,id);
    }

}
