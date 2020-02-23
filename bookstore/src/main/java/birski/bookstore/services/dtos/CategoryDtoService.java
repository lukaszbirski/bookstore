package birski.bookstore.services.dtos;

import birski.bookstore.exceptions.NameException;
import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.mappers.CategoryMapper;
import birski.bookstore.models.dtos.CategoryDto;
import birski.bookstore.repositories.CategoryRepository;
import birski.bookstore.services.validation.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryDtoService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private MapValidationErrorService mapValidationErrorService;

    public CategoryDtoService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, MapValidationErrorService mapValidationErrorService) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    public List<CategoryDto> getCategoriesDto(){
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categoryRepository.findAll().forEach(c -> categoryDtos.add(categoryMapper.map(c)));
        return categoryDtos;
    }

    public CategoryDto getCategory(String categoryName){
        return categoryRepository.getCategoryByCategoryName(categoryName).map(c -> {
            CategoryDto categoryDto = categoryMapper.map(c);
            return categoryDto;
        }).orElseThrow(() -> new ResourceNotFoundException("Category name: " + categoryName + " not found."));
    }

    public ResponseEntity<?> createCategoryDto(CategoryDto categoryDto, BindingResult bindingResult){

        try{
            ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
            if (errors != null) return errors;
            categoryRepository.save(categoryMapper.reverse(categoryDto));
            return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.CREATED);
        }catch (Exception e){
            throw new NameException("Category name " + categoryDto.getCategoryName() + " already exists");
        }
    }

    public ResponseEntity<?> updateCategoryDto(String categoryName, CategoryDto categoryDto, BindingResult bindingResult){
        ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
        if (errors != null) return errors;
        return categoryRepository.getCategoryByCategoryName(categoryName).map(c -> {
            c.setCategoryName(categoryDto.getCategoryName());
            categoryMapper.map(categoryRepository.save(c));
            return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("Category name: " + categoryName + " not found."));
    }

    public ResponseEntity<?> deleteCategoryDto(String categoryName){
        return categoryRepository.getCategoryByCategoryName(categoryName).map(c ->{
            categoryRepository.delete(c);
            return new ResponseEntity<>("Category name: " + categoryName + " was deleted!", HttpStatus.OK);
        }).orElseThrow(()-> new ResourceNotFoundException("Category name: " + categoryName + " not found."));
    }
}
