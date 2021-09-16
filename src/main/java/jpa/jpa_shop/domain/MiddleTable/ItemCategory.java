package jpa.jpa_shop.domain.MiddleTable;

import jpa.jpa_shop.domain.BaseEntity;
import jpa.jpa_shop.domain.category.Category;
import jpa.jpa_shop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "item_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ItemCategory extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="item_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

}
