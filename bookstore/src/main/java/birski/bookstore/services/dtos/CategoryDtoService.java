package birski.bookstore.services.dtos;

import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.mappers.CategoryMapper;
import birski.bookstore.models.dtos.CategoryDto;
import birski.bookstore.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryDtoService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryDtoService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getCategories(){
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

    public ResponseEntity<CategoryDto> createCategoryDto(CategoryDto categoryDto){
        categoryRepository.save(categoryMapper.reverse(categoryDto));
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.CREATED);
    }

    public CategoryDto updateCategoryDto(String categoryName, CategoryDto categoryDto){
        return categoryRepository.getCategoryByCategoryName(categoryName).map(c -> {
            c.setCategoryName(categoryDto.getCategoryName());
            return categoryMapper.map(categoryRepository.save(c));
        }).orElseThrow(() -> new ResourceNotFoundException("Category name: " + categoryName + " not found."));
    }

    public ResponseEntity<?> deleteCategoryDto(String categoryName){
        return categoryRepository.getCategoryByCategoryName(categoryName).map(c ->{
            categoryRepository.delete(c);
            return new ResponseEntity<>("Category name: " + categoryName + " was deleted!", HttpStatus.OK);
        }).orElseThrow(()-> new ResourceNotFoundException("Category name: " + categoryName + " not found."));
    }

    //todo do dodawania dodać zabezpieczenie przez stworzeniem dwóch takich samych rekordów
}
