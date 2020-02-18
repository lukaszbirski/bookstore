package birski.bookstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books", schema = "public")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "Title is required")
    private String title;

    //@NotBlank(message = "Author is required")
    private String author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_categories", joinColumns = {@JoinColumn(name = "books_id")},
    inverseJoinColumns = {@JoinColumn(name = "categories_id")})
    private Set<Category> categories = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private CoverType coverType;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comment> comments = new HashSet<>();

    private String fileName;

    public Book(String title, String author, Set<Category> categories, CoverType coverType, String publisher, String description, String ean, int pages, double price, Date releaseDate, Set<Comment> comments, String fileName) {
        this.title = title;
        this.author = author;
        this.categories = categories;
        this.coverType = coverType;
        this.publisher = publisher;
        this.description = description;
        this.ean = ean;
        this.pages = pages;
        this.price = price;
        this.releaseDate = releaseDate;
        this.comments = comments;
        this.fileName = fileName;
    }

    public Book() {
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public CoverType getCoverType() {
        return coverType;
    }

    public void setCoverType(CoverType coverType) {
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", categories=" + categories +
                ", coverType=" + coverType +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                ", ean=" + ean +
                ", pages=" + pages +
                ", price=" + price +
                ", releaseDate='" + releaseDate + '\'' +
                ", comments=" + comments +
                '}';
    }
}
