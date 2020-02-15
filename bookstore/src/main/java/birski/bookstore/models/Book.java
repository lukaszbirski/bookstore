package birski.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books", schema = "public")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String author;

    //@JsonIgnore  //todo usunąć JsonIgnora
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_categories", joinColumns = {@JoinColumn(name = "books_id")},
    inverseJoinColumns = {@JoinColumn(name = "categories_id")})
    private Set<Category> categories = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private CoverType coverType;

    @NotNull
    private String publisher;

    @NotNull
    private String description;

    @NotNull
    private long ean;

    @NotNull
    private int pages;

    @NotNull
    private double price;

    //todo poprawić date
    @NotNull
    private String releaseDate;

    //@JsonIgnore //todo usunąć JsonIgnora
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comment> comments = new HashSet<>();

    public Book(String title, String author, Set<Category> categories, CoverType coverType, String publisher, String description, long ean, int pages, double price, String releaseDate, Set<Comment> comments) {
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

    public long getEan() {
        return ean;
    }

    public void setEan(long ean) {
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
