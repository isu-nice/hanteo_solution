package hanteo.solution.question1.category.service;

import hanteo.solution.question1.category.dto.CategoriesResponseDto;
import hanteo.solution.question1.category.dto.CategoryDto;
import hanteo.solution.question1.category.dto.CategoryResponseDto;
import hanteo.solution.question1.category.entity.Category;
import hanteo.solution.question1.category.mapper.CategoryMapper;
import hanteo.solution.question1.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static hanteo.solution.question1.exception.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private static final String ANONYMOUS = "익명게시판";

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    // 카테고리명으로 검색
    public CategoriesResponseDto findCategoryByName(String categoryName) {
        List<Category> categories = findAllCategories(categoryName);

        return mapper.mapCategoriesToDto(categories);
    }

    // 카테고리 식별자로 검색
    public CategoryResponseDto findCategoryById(String categoryId) {
        Long id = parseCategoryId(categoryId);
        Category category = findCategory(id);
        return mapper.mapCategoryToDto(category);
    }

    // category 생성
    public Category createCategory(CategoryDto dto) {
        Category category = mapper.categoryDtoToCategory(dto);

        setParent(dto, category);
        setChildId(category, dto.getName());

        return repository.save(category);
    }

    private List<Category> findAllCategories(String categoryName) {
        List<Category> categories = repository.findAllByName(categoryName);
        if (categories.isEmpty()) {
            throw new IllegalArgumentException(CATEGORY_NOT_FOUND.getMessage());
        }
        return categories;
    }

    private List<CategoryResponseDto> mapCategoriesToResponseDto(List<Category> categories) {
        List<CategoryResponseDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponseDto responseDto = mapper.mapCategoryToDto(category);
            categoryDtos.add(responseDto);
        }
        return categoryDtos;
    }

    // 카테고리 식별자를 파싱하는 메서드
    private Long parseCategoryId(String categoryId) {
        try {
            return Long.parseLong(categoryId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_CATEGORY_ID_FORMAT.getMessage());
        }
    }

    // 카테고리를 식별자로 검색하는 메서드
    private Category findCategory(Long categoryId) {
        return repository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException(CATEGORY_NOT_FOUND.getMessage()));
    }

    // parent 설정 메서드
    private void setParent(CategoryDto dto, Category category) {
        Long parentIdx = dto.getParentIdx();

        if (parentIdx != null) {
            Category parentCategory = getParentCategory(parentIdx);
            category.setParent(parentCategory);
        }
    }

    // 부모 카테고리를 가져오는 메서드
    private Category getParentCategory(Long parentIdx) {
        return repository.findById(parentIdx)
                .orElseThrow(() -> new IllegalArgumentException(PARENT_CATEGORY_NOT_FOUND.getMessage()));
    }

    /**
     * childId 설정 메서드
     * 익명게시판인 경우 같은 child_id 부여
     * 나머지는 child_id 증가시킨 번호 부여
     */
    private void setChildId(Category category, String categoryName) {
        if (categoryName.equals(ANONYMOUS)) {
            Category anonymous = getAnonymousCategory();
            category.setChildId(anonymous.getChildId());
        } else {
            Long maxChildId = repository.getMaxChildId();
            category.setChildId(maxChildId + 1);
        }
    }

    // 익명게시판 카테고리를 가져오는 메서드
    private Category getAnonymousCategory() {
        return repository.findFirstByName(ANONYMOUS);
    }
}
