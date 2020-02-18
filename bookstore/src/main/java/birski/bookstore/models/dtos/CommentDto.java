package birski.bookstore.models.dtos;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

public class CommentDto {

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Description is required")
    private String description;

    @Digits(integer = 1, fraction = 1, message = "Invalid value")
    @NotBlank(message = "Rating is required")
    @Range(min = 0, max = 5, message = "Rating should be between 0 and 5")
    private float rating;

    @NotBlank(message = "Date is required")
    private String date;

    @NotBlank(message = "Book title is required")
    private String bookTitle;

    public CommentDto() {
    }

    public CommentDto(String author, String description, float rating, String date, String bookTitle) {
        this.author = author;
        this.description = description;
        this.rating = rating;
        this.date = date;
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
