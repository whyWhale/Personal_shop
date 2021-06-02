package jpa.jpa_shop.web.dto.request.item;

import jpa.jpa_shop.domain.item.Movie;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class MovieSaveRequestDto {
    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @Min(value = 1000,message = "1000원 이상이여야 합니다.")
    private int price;

    @Min(value = 1,message = "1개 이상이여야 합니다.")
    private int stockQuantity;

    @NotEmpty(message = "감독을 입력해주세요.")
    private String director;

    @NotEmpty(message = "주연 배우를 입력해주세요.")
    private String actor;

    public Movie toEntity()
    {
        return Movie.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .director(director)
                .actor(actor)
                .build();
    }
}
