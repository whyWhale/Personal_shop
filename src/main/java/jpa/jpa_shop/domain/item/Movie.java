package jpa.jpa_shop.domain.item;

import jpa.jpa_shop.web.controller.dto.response.BookUpdateResponseDto;
import jpa.jpa_shop.web.controller.dto.response.MovieUpdateResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
@Getter
@DiscriminatorValue("M")
@NoArgsConstructor
@Entity
public class Movie extends Item{
    private String director;

    private String actor;

    @Builder
    public Movie(String name, int price, int stockQuantity, String director, String actor) {
        super(name, price, stockQuantity);
        this.director = director;
        this.actor = actor;
    }

    public MovieUpdateResponseDto toEntity()
    {
        return MovieUpdateResponseDto.builder()
                .id(getId())
                .name(getName())
                .price(getPrice())
                .stockQuantity(getStockQuantity())
                .director(director)
                .actor(actor)
                .build();
    }

}
