package hanteo.solution.question1.category.mapper;

import hanteo.solution.question1.category.dto.CategoriesResponseDto;
import hanteo.solution.question1.category.dto.CategoryDto;
import hanteo.solution.question1.category.dto.CategoryResponseDto;
import hanteo.solution.question1.category.entity.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {

    default CategoriesResponseDto mapCategoriesToDto(List<Category> children) {
        List<CategoryResponseDto> categoryResponseDtos = mapChildrenToDto(children);
        return CategoriesResponseDto.builder()
                .category(categoryResponseDtos)
                .build();
    }

    default CategoryResponseDto mapCategoryToDto(Category category) {
        Long parentId = category.getParent() != null ? category.getParent().getId() : 0L;

        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .childId(category.getChildId())
                .parentIdx(parentId)
                .children(mapChildrenToDto(category.getChildren()))
                .build();
    }

    private List<CategoryResponseDto> mapChildrenToDto(List<Category> children) {
        return children.stream()
                .map(this::mapCategoryToDto)
                .collect(Collectors.toList());
    }

    Category categoryDtoToCategory(CategoryDto requestBody);

}
