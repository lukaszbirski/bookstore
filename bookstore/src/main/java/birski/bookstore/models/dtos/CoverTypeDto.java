package birski.bookstore.models.dtos;

import java.util.ArrayList;
import java.util.List;

public class CoverTypeDto {

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