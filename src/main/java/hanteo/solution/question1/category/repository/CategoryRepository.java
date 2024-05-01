package hanteo.solution.question1.category.repository;

import hanteo.solution.question1.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findFirstByName(String categoryName);

    List<Category> findAllByName(String categoryName);

    @Query("SELECT MAX(c.childId) FROM Category c")
    Long getMaxChildId();
}
