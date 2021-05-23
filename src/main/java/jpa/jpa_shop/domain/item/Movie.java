package jpa.jpa_shop.domain.item;

import jpa.jpa_shop.web.controller.dto.response.item.MovieUpdateResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Getter
@DiscriminatorValue("M")
@NoArgsConstructor
@DynamicUpdate
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

    @Override
    public void update(Item item) {
        Movie dto=(Movie) item;
        this.parentUpdate(item);
        this.director=dto.getDirector();
        this.actor=dto.getActor();
    }
}
