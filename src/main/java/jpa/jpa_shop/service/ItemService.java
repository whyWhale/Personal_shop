package jpa.jpa_shop.service;

import jpa.jpa_shop.domain.item.Item;
import jpa.jpa_shop.domain.item.Repository.ItemRepository;
import jpa.jpa_shop.exception.NotSearchId;
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
        Item item = itemRepository.findById(itemId);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+item.getClass());
        if(item==null)
        {
            throw new NotSearchId("존재하지 않는 상품입니다.");
        }
        return item;
    }
}
