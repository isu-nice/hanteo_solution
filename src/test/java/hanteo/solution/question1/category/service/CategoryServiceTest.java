package hanteo.solution.question1.category.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static hanteo.solution.question1.exception.ExceptionMessage.CATEGORY_NOT_FOUND;
import static hanteo.solution.question1.exception.ExceptionMessage.INVALID_CATEGORY_ID_FORMAT;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryService service;

    @Test
    @DisplayName("카테고리명 검색 - 성공")
    public void getCategoryByName_exist() throws Exception {
        // given
        String categoryName = "엑소";

        // when, then
        mockMvc.perform(get("/categories/{categoryName}", categoryName))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=UTF-8"))
                .andExpect(jsonPath("$.category[0].children", hasSize(4)))
                .andExpect(jsonPath("$.category[0].name", is(categoryName)));
    }

    @Test
    @DisplayName("카테고리명 검색 - 존재하지 않는 경우")
    public void getCategoryByName_not_found() throws Exception {
        // given
        String categoryName = "존재하지않는카테고리";

        // when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            service.findCategoryByName(categoryName);
        });

        // then
        assertEquals(ex.getMessage(), CATEGORY_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("익명게시판 검색")
    public void getCategoryByName_anonymous() throws Exception {
        // given
        String categoryName = "익명게시판";

        // when, then
        mockMvc.perform(get("/categories/{categoryName}", categoryName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.category", hasSize(2)))
                .andExpect(jsonPath("$.category[0].childId").value(11))
                .andExpect(jsonPath("$.category[1].childId").value(11))
                .andExpect(jsonPath("$.category[0].name", is(categoryName)));
    }

    @Test
    @DisplayName("공지사항 검색")
    public void getCategoryByName_notice() throws Exception {
        // given
        String categoryName = "공지사항";

        // when, then
        mockMvc.perform(get("/categories/{categoryName}", categoryName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.category", hasSize(3)))
                .andExpect(jsonPath("$.category[0].childId").value(6))
                .andExpect(jsonPath("$.category[2].childId").value(13))
                .andExpect(jsonPath("$.category[0].name", is(categoryName)));
    }

    @Test
    @DisplayName("카테고리 ID로 검색 - 존재하는 경우")
    public void getCategoryById_exist() throws Exception {
        // given
        String categoryId = "3";

        // when, then
        mockMvc.perform(get("/categories?id={categoryId}", categoryId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(Integer.parseInt(categoryId))))
                .andExpect(jsonPath("$.children", hasSize(4)));
    }

    @Test
    @DisplayName("카테고리 ID로 검색 - 존재하지 않는 경우")
    public void getCategoryById_not_found() {
        // given
        String categoryId = "989";

        // when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            service.findCategoryById(categoryId);
        });

        // then
        assertEquals(ex.getMessage(), CATEGORY_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("카테고리 ID로 검색 - 입력 형식 (숫자) 오류")
    public void getCategoryById_invalid() {
        // given
        String categoryId = "숫자아님";

        // when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            service.findCategoryById(categoryId);
        });

        // then
        assertEquals(ex.getMessage(), INVALID_CATEGORY_ID_FORMAT.getMessage());
    }

}