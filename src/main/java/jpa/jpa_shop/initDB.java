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
        initService.dbInit2();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        public void dbInit()
        {
            Member member = Member.builder().
                    name("PARK").
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

            Book book = Book.builder()
                    .author("구종만")
                    .isbn("isbn")
                    .name("알고리즘 문제 해결 전략")
                    .price(33000)
                    .stockQuantity(10)
                    .build();

            em.persist(movie);
            em.persist(book);

            OrderItem orderItem = OrderItem.createOrderItem(book, book.getPrice(), 2);
            OrderItem orderItem2 = OrderItem.createOrderItem(movie, movie.getPrice(), 1);
            Order order = Order.createOrder(member,
                    Delivery.builder().address(member.getAddress()).status(DeliveryStatus.READY).build()
                    , orderItem, orderItem2);
            em.persist(order);
        }

        public void dbInit2()
        {
            Member member = Member.builder().
                    name("PARK").
                    address(Address.builder().city("Seoul").street("gogo street").zipcode("11-1").build())
                    .build();

            em.persist(member);

            Album album = Album.builder()
                    .artist("IU")
                    .name("밤 편지")
                    .price(55000)
                    .stockQuantity(10)
                    .build();

            Album album2 = Album.builder()
                    .artist("IU")
                    .name("라일락")
                    .price(65000)
                    .stockQuantity(10)
                    .build();

            em.persist(album);
            em.persist(album2);

            OrderItem orderItem=OrderItem.createOrderItem(album,album.getPrice(),5);
            OrderItem orderItem2=OrderItem.createOrderItem(album2,album2.getPrice(),2);
            Order order = Order.createOrder(member,
                    Delivery.builder().address(member.getAddress()).status(DeliveryStatus.READY).build(),
                    orderItem, orderItem2);

            em.persist(order);
        }
    }
}


