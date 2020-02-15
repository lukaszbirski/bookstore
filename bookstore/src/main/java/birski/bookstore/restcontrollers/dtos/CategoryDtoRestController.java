package birski.bookstore.restcontrollers.dtos;

import birski.bookstore.models.Category;
import birski.bookstore.models.dtos.CategoryDto;
import birski.bookstore.services.dtos.CategoryDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static birski.bookstore.configs.ApiConfig.*;

@RestController
@RequestMapping(API_URL + DTO_URL + CATEGORIES_URL)
public class CategoryDtoRestController {

    private CategoryDtoService categoryDtoService;

    public CategoryDtoRestController(CategoryDtoService categoryDtoService) {
        this.categoryDtoService = categoryDtoService;
    }

    @GetMapping()
    public List<CategoryDto> getCategoriesDto(){
        return categoryDtoService.getCategories();
    }

    @GetMapping(NAME_URL)
    public CategoryDto getCategoryDto(@PathVariable String name){
        return categoryDtoService.getCategory(name);
    }

    @PostMapping()
    public ResponseEntity<CategoryDto> createCategoryDto(@RequestBody CategoryDto categoryDto){
        return categoryDtoService.createCategoryDto(categoryDto);
    }

    @PutMapping(NAME_URL)
    public CategoryDto updateCategoryDto(@PathVariable ("name") String categoryName, @RequestBody CategoryDto categoryDto){
        return categoryDtoService.updateCategoryDto(categoryName, categoryDto);
    }

    @DeleteMapping(NAME_URL)
    public ResponseEntity<?> deleteCategoryDto(@PathVariable ("name") String categoryName){
        return categoryDtoService.deleteCategoryDto(categoryName);
    }
}
//todo dokończyć RestController - pozostał tylko update
