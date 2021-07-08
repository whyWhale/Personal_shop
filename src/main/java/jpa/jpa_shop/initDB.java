package jpa.jpa_shop;

import jpa.jpa_shop.domain.MiddleTable.OrderItem;
import jpa.jpa_shop.domain.delivery.Delivery;
import jpa.jpa_shop.domain.delivery.DeliveryStatus;
import jpa.jpa_shop.domain.item.Album;
import jpa.jpa_shop.domain.item.Book;
import jpa.jpa_shop.domain.item.Movie;
import jpa.jpa_shop.domain.member.Address;
import jpa.jpa_shop.domain.member.Member;
import jpa.jpa_shop.domain.orders.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit() {
            Member member = Member.builder().
                    name("KIM").
                    address(Address.builder().city("Seoul").street("soso street").zipcode("59-1").build())
                    .build();
            em.persist(member);

            Movie movie = Movie.builder()
                    .actor("송강호")
                    .director("봉준호")
                    .name("괴물")
                    .price(10000)
                    .stockQuantity(10)
                    .build();
            em.persist(movie);

            Book book = Book.builder()
                    .author("구종만")
                    .isbn("131-12-1113NQU-123")
                    .name("알고리즘 문제 해결 전략")
                    .price(33000)
                    .stockQuantity(10)
                    .build();

            em.persist(book);
            for (int i = 1; i < 1000; i++) {
                member = Member.builder().
                        name("KIM" + i).
                        address(Address.builder().city("Seoul").street("soso street").zipcode("59-1").build())
                        .build();
                em.persist(member);
                movie = Movie.builder()
                        .actor("송강호"+i)
                        .director("봉준호"+i)
                        .name("괴물"+i)
                        .price(10000)
                        .stockQuantity(10)
                        .build();
                em.persist(movie);

                 book = Book.builder()
                        .author("구종만"+i)
                        .isbn("131-12-1113NQU-123"+i)
                        .name("알고리즘 문제 해결 전략"+i)
                        .price(33000)
                        .stockQuantity(10)
                        .build();
                 em.persist(book);
            }

            OrderItem orderItem = OrderItem.createOrderItem(book, book.getPrice(), 2);
            OrderItem orderItem2 = OrderItem.createOrderItem(movie, movie.getPrice(), 1);
            Order order = Order.createOrder(member,
                    Delivery.builder().address(member.getAddress()).status(DeliveryStatus.READY).build()
                    , orderItem, orderItem2);
            em.persist(order);
        }

    }
}


