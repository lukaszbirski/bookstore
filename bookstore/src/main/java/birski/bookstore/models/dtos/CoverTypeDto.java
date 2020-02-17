package birski.bookstore.models.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class CoverTypeDto {

    @NotBlank(message = "Field is required")
    @Column(unique = true)
    private String name;
    private List<String> books = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public CoverTypeDto(String name, List<String> books) {
        this.name = name;
        this.books = books;
    }
}