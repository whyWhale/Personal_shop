package jpa.jpa_shop.web.dto.request.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


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
