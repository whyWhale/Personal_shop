package jpa.jpa_shop.web.controller.dto.response;

import jpa.jpa_shop.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
public class ItemListResponseDto extends Item {
    private Long id;
    private String type;

    @Builder
    public ItemListResponseDto(String name, int price, int stockQuantity, String type, Long id) {
        super(name, price, stockQuantity);
        this.id=id;
        this.type=type;
    }


}
