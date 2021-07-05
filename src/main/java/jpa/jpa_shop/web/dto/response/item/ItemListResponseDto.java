package jpa.jpa_shop.web.dto.response.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ItemListResponseDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String type;

    @Builder
    public ItemListResponseDto(Long id, String name, int price, int stockQuantity, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.type = type;
    }

}
