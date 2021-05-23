package jpa.jpa_shop.domain.item;

import jpa.jpa_shop.web.controller.dto.response.item.BookUpdateResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@DiscriminatorValue("B")
@DynamicUpdate
@Entity
public class Book extends Item{
    private String author;

    private String isbn;

    @Builder
    public Book(String name, int price, int stockQuantity, String author, String isbn) {
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    public BookUpdateResponseDto toEntity()
    {
        return BookUpdateResponseDto.builder()
                .id(getId())
                .name(getName())
                .price(getPrice())
                .stockQuantity(getStockQuantity())
                .author(author)
                .isbn(isbn)
                .build();
    }

    @Override
    public void update(Item item) {
        Book dto=(Book) item;
        this.parentUpdate(item);
        this.author= dto.getAuthor();
        this.isbn= dto.getIsbn();
    }
}
