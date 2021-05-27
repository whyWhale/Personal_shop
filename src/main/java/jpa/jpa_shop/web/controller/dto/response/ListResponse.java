package jpa.jpa_shop.web.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListResponse<T> {
    private int count;
    private T data;

}

