package jpa.jpa_shop.web.controller.dto.request.item;

import jpa.jpa_shop.domain.item.Album;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class AlbumSaveRequestDto {
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

    public Album toEntity()
    {
        return Album.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .artist(artist)
                .etc(etc)
                .build();
    }

}
