package birski.bookstore.models.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

    @NotBlank(message = "Field is required")
    @Column(unique = true)
    private String categoryName;
    private List<String> books = new ArrayList<>();

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public CategoryDto() {
    }

    public CategoryDto(String categoryName, List<String> books) {
        this.categoryName = categoryName;
        this.books = books;
    }
}