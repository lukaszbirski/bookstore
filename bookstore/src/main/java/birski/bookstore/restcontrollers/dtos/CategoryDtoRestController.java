package birski.bookstore.restcontrollers.dtos;

import birski.bookstore.models.dtos.CategoryDto;
import birski.bookstore.services.dtos.CategoryDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return categoryDtoService.getCategoriesDto();
    }

    @GetMapping("/{name}")
    public CategoryDto getCategoryDto(@PathVariable String name){
        return categoryDtoService.getCategory(name);
    }

    @PostMapping()
    public ResponseEntity<?> createCategoryDto(@Valid @RequestBody CategoryDto categoryDto, BindingResult bindingResult){
        return categoryDtoService.createCategoryDto(categoryDto, bindingResult);
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateCategoryDto(@PathVariable ("name") String categoryName, @Valid @RequestBody CategoryDto categoryDto, BindingResult bindingResult){
        return categoryDtoService.updateCategoryDto(categoryName, categoryDto, bindingResult);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteCategoryDto(@PathVariable ("name") String categoryName){
        return categoryDtoService.deleteCategoryDto(categoryName);
    }
}
