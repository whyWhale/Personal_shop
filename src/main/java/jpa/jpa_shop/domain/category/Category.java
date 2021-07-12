package jpa.jpa_shop.domain.category;

import jpa.jpa_shop.domain.BaseEntity;
import jpa.jpa_shop.domain.MiddleTable.ItemCategory;
import jpa.jpa_shop.domain.item.Item;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Setter(AccessLevel.PRIVATE)
@Getter
@ToString(exclude = {"child"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @Builder
    public Category(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category")
    private List<ItemCategory> itemCategories=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child=new LinkedList<>();

    public void addChildCategory(Category child)
    {
        this.child.add(child);
        child.setParent(this);
    }

}
