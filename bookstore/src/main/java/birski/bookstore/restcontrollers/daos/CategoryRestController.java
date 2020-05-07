package birski.bookstore.restcontrollers.daos;

import birski.bookstore.models.daos.Category;
import birski.bookstore.services.daos.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static birski.bookstore.configs.ApiConfig.*;

@RestController
@RequestMapping(API_URL + CATEGORIES_URL)
public class CategoryRestController {

    private CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping()
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable long id, @RequestBody Category category){
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable long id){
        return categoryService.deleteCategory(id);
    }
}
