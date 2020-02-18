package birski.bookstore.restcontrollers.dtos;

import birski.bookstore.models.Category;
import birski.bookstore.models.dtos.CategoryDto;
import birski.bookstore.services.dtos.CategoryDtoService;
import birski.bookstore.services.validation.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return categoryDtoService.getCategoriesDto();
    }

    @GetMapping(NAME_URL)
    public CategoryDto getCategoryDto(@PathVariable String name){
        return categoryDtoService.getCategory(name);
    }

    @PostMapping()
    public ResponseEntity<?> createCategoryDto(@Valid @RequestBody CategoryDto categoryDto, BindingResult bindingResult){
        return categoryDtoService.createCategoryDto(categoryDto, bindingResult);
    }

    @PutMapping(NAME_URL)
    public ResponseEntity<?> updateCategoryDto(@PathVariable ("name") String categoryName, @Valid @RequestBody CategoryDto categoryDto, BindingResult bindingResult){
        return categoryDtoService.updateCategoryDto(categoryName, categoryDto, bindingResult);
    }

    @DeleteMapping(NAME_URL)
    public ResponseEntity<?> deleteCategoryDto(@PathVariable ("name") String categoryName){
        return categoryDtoService.deleteCategoryDto(categoryName);
    }
}
