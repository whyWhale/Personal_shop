package jpa.jpa_shop.web.controller.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
public class BookUpdateResponseDto {
    Long id;
    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @Min(value = 1000,message = "1000원 이상이여야 합니다.")
    private int price;

    @Min(value = 1,message = "1개 이상이여야 합니다.")
    private int stockQuantity;

    @NotEmpty(message = "저자를 입력해주세요.")
    private String author;

    @NotEmpty(message = "저자를 입력해주세요.")
    private String isbn;

    @Builder
    public BookUpdateResponseDto(Long id,String name, int price, int stockQuantity, String author, String isbn) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }

}
