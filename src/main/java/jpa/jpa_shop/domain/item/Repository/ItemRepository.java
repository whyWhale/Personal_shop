package jpa.jpa_shop.domain.item.Repository;

import jpa.jpa_shop.domain.item.Album;
import jpa.jpa_shop.domain.item.Book;
import jpa.jpa_shop.domain.item.Item;
import jpa.jpa_shop.domain.item.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>, QuerydslPredicateExecutor<Item> {

}
