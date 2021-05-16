package jpa.jpa_shop.domain.item;

import jpa.jpa_shop.domain.category.Category;
import jpa.jpa_shop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@NoArgsConstructor
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Entity
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    Long id;

    private String name;
    private int price;
    private int stockQuantity;

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    @ManyToMany(mappedBy = "items")
    private List<Category> categorys=new LinkedList<>();

    public void addStock(int quantity)
    {
        this.stockQuantity+=quantity;
    }
    public void removeStock(int quantity) {
        int restStockQuantity = this.stockQuantity - quantity;
        if(restStockQuantity<0)
            throw new NotEnoughStockException("Stock is less than 0!");
        this.stockQuantity=restStockQuantity;
    }

}
