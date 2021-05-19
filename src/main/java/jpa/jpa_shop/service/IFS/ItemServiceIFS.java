package jpa.jpa_shop.service.IFS;

import jpa.jpa_shop.domain.item.Item;
import jpa.jpa_shop.web.controller.dto.response.ItemListResponseDto;

import java.util.List;

public interface ItemServiceIFS {
    public void saveItem(Item item);

    public List<ItemListResponseDto> findItems();

    public Item findById(Long itemId);

    public void updateItem(Item item);
}
