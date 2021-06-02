package jpa.jpa_shop.web.controller.dto.request.order;

import jpa.jpa_shop.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Map;


@NoArgsConstructor
@Getter
public class OrderSaveRequestDto {

    @NotEmpty(message = "required..")
    private String memberId;

    @NotEmpty(message = "상품을 선택해주세요.")
    private Long items[];

    @NotEmpty(message = "갯수를 입력해주세요.")
    private int count[];


    @Builder
    public OrderSaveRequestDto(String memberId,Long[] items, int[] count) {
        this.memberId = memberId;
        this.items = items;
        this.count = count;
    }


}
