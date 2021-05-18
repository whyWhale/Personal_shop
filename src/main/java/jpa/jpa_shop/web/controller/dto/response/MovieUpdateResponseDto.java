package jpa.jpa_shop.web.controller.dto.response;

import jpa.jpa_shop.domain.item.Movie;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class MovieUpdateResponseDto {
    private Long id;

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


    @Builder
    public MovieUpdateResponseDto(Long id, String name, int price, int stockQuantity, String director, String actor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.director = director;
        this.actor = actor;
    }
    public Movie toEntity()
    {
        Movie movie = Movie.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .director(director)
                .actor(actor)
                .build();
        movie.setId(id);
        return movie;
    }

}
