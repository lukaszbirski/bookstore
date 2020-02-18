package birski.bookstore.models.dtos;

import birski.bookstore.models.Category;
import birski.bookstore.models.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;

public class BookDto {

    //@NotBlank(message = "Title is required")
    private String title;
    //@NotBlank(message = "Author is required")
    private String author;

    private List<String> categories = new ArrayList<>();

    private String coverType;

    //@NotBlank(message = "Publisher is required")
    private String publisher;

    //@NotBlank(message = "Description is required")
    private String description;

    //@Size(min = 12, max = 13, message = "Ean number should have between 12 and 13 digits")
    //@NotBlank(message = "Ean number is required")
    //@Pattern(regexp="\\d", message = "Ean number should have between 12 and 13 digits")
    private String ean;

    //@NotBlank(message = "Number of pages is required")
    private int pages;

    //@NotBlank(message = "Price is required")
    //@Digits(integer = 4, fraction = 2, message = "Invalid value")
    private double price;

    //@NotBlank(message = "Release date is required")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String releaseDate;

    private List<CommentDto> comments = new ArrayList<>();

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
