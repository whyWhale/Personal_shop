package jpa.jpa_shop.service;

import jpa.jpa_shop.domain.item.Item;
import jpa.jpa_shop.domain.item.Repository.ItemRepository;
import jpa.jpa_shop.service.IFS.ItemServiceIFS;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService implements ItemServiceIFS {
    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(Long itemId) {
        return itemRepository.find(itemId);
    }
}
