package cz.mendelu.ea.domain.category;

import cz.mendelu.ea.domain.studio.StudioResponse;
import cz.mendelu.ea.utils.exceptions.NotFoundException;
import cz.mendelu.ea.utils.response.ArrayResponse;
import cz.mendelu.ea.utils.response.ObjectResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "", produces = "application/json")
    @Valid
    public ArrayResponse<CategoryResponse> getAllCategories() {
        return ArrayResponse.of(
                categoryService.getAllCategories(),
                CategoryResponse::new
        );
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Valid
    public ObjectResponse<CategoryResponse> getCategoryById(@PathVariable int id) {
        Category category = categoryService
                .findCategoryById(id)
                .orElseThrow(NotFoundException::new);
        return ObjectResponse.of(category, CategoryResponse::new);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

/*    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }*/

/*    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }*/
}