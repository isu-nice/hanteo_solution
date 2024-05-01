package hanteo.solution.question1.category.controller;

import hanteo.solution.question1.category.dto.CategoriesResponseDto;
import hanteo.solution.question1.category.dto.CategoryDto;
import hanteo.solution.question1.category.dto.CategoryResponseDto;
import hanteo.solution.question1.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories", produces = "application/json; charset=UTF-8")
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/{categoryName}")
    public ResponseEntity getCategoryByName(@PathVariable String categoryName) {
        CategoriesResponseDto response = service.findCategoryByName(categoryName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCategoryById(@RequestParam("id") String categoryId) {
        CategoryResponseDto response = service.findCategoryById(categoryId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CategoryDto request) {
        service.createCategory(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
