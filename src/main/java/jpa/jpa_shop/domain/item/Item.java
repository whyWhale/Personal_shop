package jpa.jpa_shop.domain.item;

import jpa.jpa_shop.domain.BaseEntity;
import jpa.jpa_shop.domain.MiddleTable.ItemCategory;
import jpa.jpa_shop.domain.category.Category;
import jpa.jpa_shop.exception.NotEnoughStockException;
import jpa.jpa_shop.web.dto.response.item.ItemListResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@DynamicUpdate
@Entity
public abstract class Item extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    private boolean display;

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.display=true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemCategory> itemCategories=new LinkedList<>();


    public ItemListResponseDto toResponseDTO(String type)
    {
        return ItemListResponseDto.builder()
                .id(id)
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .type(type)
                .build();
    }

    public void addStock(int quantity)
    {
        this.stockQuantity+=quantity;
    }
    public void removeStock(int quantity) {
        int restStockQuantity = this.stockQuantity - quantity;
        if(restStockQuantity<0)
            throw new NotEnoughStockException("Stock is less than 0!");
        if(restStockQuantity==0)
            this.display=false;
        this.stockQuantity=restStockQuantity;
    }

    public abstract void update(Item item);

    public void parentUpdate(Item dto)
    {
        this.name= dto.getName();
        this.price=dto.getPrice();
        this.stockQuantity= dto.getStockQuantity();

    }
}
