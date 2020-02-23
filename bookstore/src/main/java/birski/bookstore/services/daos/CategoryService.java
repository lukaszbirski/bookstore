package birski.bookstore.services.daos;

import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.models.daos.Category;
import birski.bookstore.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category getCategoryById(long id){
        return categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category id: " + id + " not found."));
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category updateCategory(long id, Category category){
        return categoryRepository.findById(id).map(c -> {
            c.setCategoryName(category.getCategoryName());
            c.setBooks(category.getBooks());
            return categoryRepository.save(c);
        }).orElseThrow(()-> new ResourceNotFoundException("Category id: " + id + " not found."));
    }

    public ResponseEntity<?> deleteCategory(long id){
        return categoryRepository.findById(id).map(c ->{
            categoryRepository.delete(c);
            return new ResponseEntity<>("Category id: " + id + " was deleted!", HttpStatus.OK);
        }).orElseThrow(()-> new ResourceNotFoundException("Category id: " + id + " not found."));
    }
}
