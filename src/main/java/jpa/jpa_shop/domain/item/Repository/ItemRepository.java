package jpa.jpa_shop.domain.item.Repository;

import jpa.jpa_shop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ItemRepository {
    private final EntityManager em;

    public Long save(Item item)
    {
        if(item.getId()==null)
        {
            em.persist(item);
        }
        else
        {
            em.merge(item);
        }

        return item.getId();
    }

    public Item findById(Long id)
    {
        return em.find(Item.class,id);
    }

    public List<Item> findAll()
    {
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }

    public void delete(Item item) {
        em.remove(item);
    }

}
