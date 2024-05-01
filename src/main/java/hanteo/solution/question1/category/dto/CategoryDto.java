package hanteo.solution.question1.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryDto {
    private String name;
    private Long parentIdx;
}
