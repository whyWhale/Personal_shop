package jpa.jpa_shop.service;

import jpa.jpa_shop.domain.item.Book;
import jpa.jpa_shop.domain.item.Item;
import jpa.jpa_shop.domain.member.Address;
import jpa.jpa_shop.domain.member.Member;
import jpa.jpa_shop.domain.orders.Order;
import jpa.jpa_shop.domain.orders.OrderStatus;
import jpa.jpa_shop.domain.orders.Repository.OrderRepository;
import jpa.jpa_shop.exception.NotEnoughStockException;
import jpa.jpa_shop.service.IFS.OrderServiceIFS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderServiceIFS orderService;
    @Autowired
    OrderRepository orderRepository;

    @Rollback(value = true)
    @Test
    public void order() {
        // Given
        Member member=createMember();
        Item item=createBook();
        int orderCnt=3;
        //When
        Long orderId = orderService.order(member.getId(), item.getId(), orderCnt);

        //then
        Order order = orderRepository.findById(orderId);
        assertThat(OrderStatus.ORDER).isEqualTo(order.getStatus());
        assertThat(1).isEqualTo(order.getOrderItems().size());
        assertThat(7).isEqualTo(item.getStockQuantity());
        assertThat(30000).isEqualTo(order.getTotalPrice());
    }
    @Test(expected = NotEnoughStockException.class)
    public void StockException() throws Exception{
        //Given
        Member member=createMember();
        Item item=createBook();
        int orderCnt=11;

        //When
        orderService.order(member.getId(),item.getId(),orderCnt);

        //Then
        fail("재고 수량 예외 발생.");
    }

    @Test
    public void cancelOrder() {
        //Given
        Member member=createMember();
        Item item=createBook();
        int orderCnt=2;

        //When
        Long orderId=orderService.order(member.getId(),item.getId(),orderCnt);
        orderService.cancelOrder(orderId);

        //Then
        Order cancelOrder = orderRepository.findById(orderId);
        assertThat(cancelOrder).isNotNull();
        assertThat(cancelOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(10);
    }

    private Member createMember()
    {
        Member member = Member.builder().
                name("PARK").
                address(Address.builder().city("Seoul").street("soso street").zipcode("59-1").build())
                .build();
        em.persist(member);
        return member;
    }
    private Book createBook()
    {
        Book book = Book.builder()
                .name("문제해결전략")
                .author("구종만")
                .isbn("111-xxxx-xxxxxx")
                .stockQuantity(10)
                .price(10000)
                .build();
        em.persist(book);
        return book;
    }
}