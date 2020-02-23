package birski.bookstore.models.daos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments", schema = "public")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String description;

    private float rating;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    public Comment() {
    }

    public Comment( String author, String description, float rating, Date date, Book book) {
        this.author = author;
        this.description = description;
        this.rating = rating;
        this.date = date;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
