package jpa.jpa_shop.domain.item.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpa.jpa_shop.domain.item.Book;
import jpa.jpa_shop.domain.item.Item;
import jpa.jpa_shop.domain.item.QBook;
import jpa.jpa_shop.domain.member.QMember;
import jpa.jpa_shop.domain.orders.Order;
import jpa.jpa_shop.web.dto.request.PageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Predicate;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>, QuerydslPredicateExecutor<Book> {

}
