package jpa.jpa_shop.web.dto.response.category;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String name;
    private Long parentId;
    private List<CategoryResponseDto> subCategory;

    @Builder
    public CategoryResponseDto(Long id, String name, Long parentId, List<CategoryResponseDto> subCategory) {
        this.id=id;
        this.name = name;
        this.parentId = parentId;
        this.subCategory = subCategory;
    }
}
