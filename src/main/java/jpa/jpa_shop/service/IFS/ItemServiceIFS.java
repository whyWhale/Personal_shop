package jpa.jpa_shop.service.IFS;

import jpa.jpa_shop.domain.item.Item;

import java.util.List;

public interface ItemServiceIFS {
    public void saveItem(Item item);

    public List<Item> findItems();

    public Item findById(Long itemId);

}
