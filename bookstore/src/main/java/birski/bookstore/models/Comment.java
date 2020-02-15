package birski.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "comments", schema = "public")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String author;

    @NotNull
    private String description;

    @NotNull
    private float rating;

    //todo poprawić date
    @NotNull
    private String date;

    @JsonIgnore  //todo usunąć JsonIgnora
    @ManyToOne
    @JoinColumn(nullable = false)
    private Book book;

    public Comment() {
    }

    public Comment(String author, String description, float rating, String date) {
        this.author = author;
        this.description = description;
        this.rating = rating;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
