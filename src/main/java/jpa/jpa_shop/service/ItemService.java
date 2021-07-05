package jpa.jpa_shop.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import jpa.jpa_shop.domain.item.*;
import jpa.jpa_shop.domain.item.Repository.ItemRepository;
import jpa.jpa_shop.exception.NoEntity;
import jpa.jpa_shop.exception.NotSearchId;
import jpa.jpa_shop.service.IFS.ItemServiceIFS;
import jpa.jpa_shop.web.dto.request.PageRequestDTO;
import jpa.jpa_shop.web.dto.request.PageResultDTO;
import jpa.jpa_shop.web.dto.response.item.ItemListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService implements ItemServiceIFS {
    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public Long saveItem(Item item) {
        return itemRepository.save(item).getId();
    }

    @Override
    public List<ItemListResponseDto> findItems(Pageable pageable) {
        Page<Item> all = itemRepository.findAll(pageable);
        List<ItemListResponseDto> collect = all.stream().map(item -> item.toResponseDTO(item.getClass().getSimpleName().toLowerCase())).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PageResultDTO<ItemListResponseDto, Item> findItems(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO); //검색 조건 처리

        Page<Item> pageTypeItem = itemRepository.findAll(booleanBuilder, pageable); //Querydsl 사용

        Function<Item, ItemListResponseDto> fn = (item -> item.toResponseDTO(item.getClass().getSimpleName().toLowerCase()));

        return new PageResultDTO<>(pageTypeItem, fn );
    }

    public List<ItemListResponseDto> findItems()
    {
        return itemRepository.findAll().stream().map(item -> item.toResponseDTO(item.getClass().getSimpleName().toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Item> findItemsToOrder() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isEmpty())
        {
            throw new NotSearchId("존재하지 않는 상품입니다.");
        }
        return item.get();
    }

    @Transactional
    @Override
    public Long updateItem(Item item) {
        Optional<Item>entityItem = itemRepository.findById(item.getId());
        switch (entityItem.getClass().getSimpleName().toLowerCase())
        {
            case "movie":
                Movie movie = (Movie) entityItem.get();
                movie.update(item);
                break;
            case "book":
                Book book = (Book) entityItem.get();
                book.update(item);
                break;
            case "album":
                Album album = (Album) entityItem.get();
                album.update(item);
                break;
        }
        return entityItem.get().getId();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Item> deleteItem = itemRepository.findById(id);
        if(deleteItem.isEmpty())
        {
            throw new NoEntity("No info");
        }
        itemRepository.delete(deleteItem.get());
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO){

        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QItem qItem = QItem.item;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qItem.id.gt(0L); // gno > 0 조건만 생성

        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0){ //검색 조건이 없는 경우
            return booleanBuilder;
        }


        //검색 조건을 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qItem.name.contains(keyword));
        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
