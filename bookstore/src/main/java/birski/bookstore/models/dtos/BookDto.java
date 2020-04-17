package birski.bookstore.models.dtos;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

public class BookDto {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;
//todo dokończyć walidację
    @NotNull(message = "Field is required")
    @NotEmpty(message = "Field is required")
    private List<String> categories = new ArrayList<>();

    @NotBlank(message = "Cover type is required")
    private String coverType;

    @NotBlank(message = "Publisher is required")
    private String publisher;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Ean number is required")
    @Size(min = 12, max = 13, message = "Ean number should have between 12 and 13 digits")
    //@Pattern(regexp="\\d", message = "Ean number should have between 12 and 13 digits")
    private String ean;

    @Min(value = 1, message = "Invalid value")
    private int pages;

    @Digits(integer = 4, fraction = 2, message = "Invalid value")
    @Min(value = 1, message = "Invalid value")
    private double price;

    @NotBlank(message = "Release date is required")
    @Pattern(regexp = "20[0-9][0-9]-[0-1][0-9]-[0-3][0-9]", message = "Write date in yyyy-mm-dd format")
    private String releaseDate;

    private List<CommentDto> comments = new ArrayList<>();

    @NotBlank(message = "File is required")
    private String fileName;

    public BookDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
