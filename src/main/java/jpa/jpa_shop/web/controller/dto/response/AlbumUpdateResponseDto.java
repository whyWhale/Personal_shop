package jpa.jpa_shop.web.controller.dto.response;

import jpa.jpa_shop.domain.item.Album;
import jpa.jpa_shop.domain.item.Item;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class AlbumUpdateResponseDto {

    private Long id;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @Min(value = 1000,message = "1000원 이상이여야 합니다.")
    private int price;

    @Min(value = 1,message = "1개 이상이여야 합니다.")
    private int stockQuantity;

    @NotEmpty(message = "아티스트를 입력해주세요")
    private String artist;

    @NotEmpty(message = "작곡가:xxx 작사가:xxx 형식으로 입력해주세요")
    private String etc;

    @Builder
    public AlbumUpdateResponseDto(Long id, String name, int price, int stockQuantity, String artist, String etc) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.artist = artist;
        this.etc = etc;
    }

    public Album toEntity()
    {
        Album album = Album.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .artist(artist)
                .etc(etc)
                .build();
        album.setId(id);
        return album;
    }
}
