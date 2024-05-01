package hanteo.solution.question1.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String name;
    private Long childId;
    private Long parentIdx;
    private List<CategoryResponseDto> children;
}