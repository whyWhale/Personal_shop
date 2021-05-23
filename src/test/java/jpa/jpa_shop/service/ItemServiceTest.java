package jpa.jpa_shop.service;

import jpa.jpa_shop.domain.item.Album;
import jpa.jpa_shop.domain.item.Book;
import jpa.jpa_shop.domain.item.Movie;
import jpa.jpa_shop.service.IFS.ItemServiceIFS;
import jpa.jpa_shop.web.controller.dto.response.item.ItemListResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemServiceIFS itemService;

    @Test
    public void SaveItemAndFindById () throws Exception{
        //given
        Movie movie = Movie.builder()
                .actor("송강호")
                .director("봉준호")
                .name("괴물")
                .price(10000)
                .build();

        Book book = Book.builder()
                .author("구종만")
                .isbn("isbn")
                .name("알고리즘 문제 해결 전략")
                .price(33000)
                .build();

        Album album = Album.builder()
                .artist("IU")
                .name("밤 편지")
                .price(55000)
                .build();
        //when
        itemService.saveItem(movie);
        itemService.saveItem(book);
        itemService.saveItem(album);
        //then
        Assertions.assertThat(movie.getPrice()).isEqualTo(itemService.findById(movie.getId()).getPrice());
        Assertions.assertThat(book.getPrice()).isEqualTo(itemService.findById(book.getId()).getPrice());
        Assertions.assertThat(album.getPrice()).isEqualTo(itemService.findById(album.getId()).getPrice());

    }
    
    @Test
    public void findItems() throws Exception{
        //given
        Movie movie = Movie.builder()
                .actor("송강호")
                .director("봉준호")
                .name("괴물")
                .price(10000)
                .build();

        Book book = Book.builder()
                .author("구종만")
                .isbn("isbn")
                .name("알고리즘 문제 해결 전략")
                .price(33000)
                .build();

        Album album = Album.builder()
                .artist("IU")
                .name("밤 편지")
                .price(55000)
                .build();
        //when
        itemService.saveItem(movie);
        itemService.saveItem(book);
        itemService.saveItem(album);
        List<ItemListResponseDto> items = itemService.findItems();
        //then
        Assertions.assertThat(items).isNotNull();
        Assertions.assertThat(items.size()).isEqualTo(3);
    }
    

}