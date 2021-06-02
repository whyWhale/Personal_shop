package jpa.jpa_shop.web.dto.response.item;

import jpa.jpa_shop.domain.item.Book;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class BookUpdateResponseDto {
    private Long id;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @Min(value = 1000, message = "1000원 이상이여야 합니다.")
    private int price;

    @Min(value = 1, message = "1개 이상이여야 합니다.")
    private int stockQuantity;

    @NotEmpty(message = "저자를 입력해주세요.")
    private String author;

    @NotEmpty(message = "저자를 입력해주세요.")
    private String isbn;

    @Builder
    public BookUpdateResponseDto(Long id, String name, int price, int stockQuantity, String author, String isbn) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }

    public Book toEntity() {
        Book book = Book.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .author(author)
                .isbn(isbn)
                .build();

        book.setId(id);
        return book;
    }

}
