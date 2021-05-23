package jpa.jpa_shop.domain.item;

import jpa.jpa_shop.web.controller.dto.response.item.AlbumUpdateResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Getter
@NoArgsConstructor
@DiscriminatorValue("A")
@DynamicUpdate
@Entity
public class Album extends Item{
    private String artist;
    private String etc;
    @Builder
    public Album(String name, int price, int stockQuantity, String artist, String etc) {
        super(name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }

    public AlbumUpdateResponseDto toEntity()
    {
        return AlbumUpdateResponseDto.builder()
                .id(getId())
                .name(getName())
                .price(getPrice())
                .stockQuantity(getStockQuantity())
                .artist(artist)
                .etc(etc)
                .build();
    }

    @Override
    public void update(Item toItem) {
        Album dto =(Album) toItem;
        this.parentUpdate(toItem);
        this.artist=dto.getArtist();
        this.etc=dto.getEtc();
    }
}
