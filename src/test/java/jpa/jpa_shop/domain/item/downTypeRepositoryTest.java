package jpa.jpa_shop.domain.item;

import jpa.jpa_shop.domain.item.Repository.BookRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@DataJpaTest
public class downTypeRepositoryTest {

    @PersistenceContext
    TestEntityManager tem;

    @Autowired
    BookRepository bookRepository;
    @Before
    public void Data() {
        for (int i = 1; i <= 20; i++) {
            Book book = Book.builder().name("" + i).stockQuantity(i)
                    .author("" + i).isbn("" + i + i + i)
                    .build();
            tem.persist(book);
            tem.flush();

            Movie movie = Movie.builder().name("" + i).stockQuantity(i)
                    .actor("" + i).director("" + i + i + i)
                    .build();
            tem.persist(movie);
            tem.flush();

            Album album = Album.builder().name("" + i).stockQuantity(i)
                    .artist("" + i).etc("" + i + i + i)
                    .build();
            tem.persist(album);
            tem.flush();
        }
    }

    @Before
    public void setData() {

        for (int i = 1; i <= 20; i++) {
            Book book = Book.builder().name("" + i).stockQuantity(i)
                    .author("" + i).isbn("" + i + i + i)
                    .build();
            tem.persist(book);
            tem.flush();
        }
    }
}