package jpa.jpa_shop.domain.category;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    TestEntityManager tem;

    @Autowired
    CategoryRepository categoryRepository;


    @Before
    public void SampleData() {

        int parent=1;
        IntStream.rangeClosed(1,10).forEach(i->{
            Category category = Category.builder().name(String.valueOf(i)).build();
            tem.persist(category);
        });
        IntStream.rangeClosed(11,20).forEach(i->{
            Category category = Category.builder().name(String.valueOf(i)).build();
            int Name = i - 10;
            Optional<Category> optionalCategory = categoryRepository.findByName( String.valueOf(Name));
            if(!optionalCategory.isEmpty())
            {
                Category parentCategory = optionalCategory.get();
                parentCategory.addChildCategory(category);
                tem.persist(category);

            }
        });
    }
    @Test
    public void findByParentIsNotNull() {
        // given

        // when
        List<Category> parentIsNotNull = categoryRepository.findByParentIsNotNull();
        // then
        assertThat(parentIsNotNull.size()).isEqualTo(10);
        int idx=1;

        for (Category category : parentIsNotNull) {
            System.out.println(category);
        }
    }
}