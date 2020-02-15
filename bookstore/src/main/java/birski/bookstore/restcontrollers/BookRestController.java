package birski.bookstore.restcontrollers;

import birski.bookstore.models.Book;
import birski.bookstore.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static birski.bookstore.configs.ApiConfig.API_URL;
import static birski.bookstore.configs.ApiConfig.BOOKS_URL;
import static birski.bookstore.configs.ApiConfig.ID_URL;

@RestController
@RequestMapping(API_URL + BOOKS_URL)
public class BookRestController {

    private BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @GetMapping(ID_URL)
    public Book getBook(@PathVariable long id){
        return bookService.getBookById(id);
    }

    @GetMapping()
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @PutMapping(ID_URL)
    public Book updateBook(@PathVariable long id, @RequestBody Book book){
        return bookService.updateBook(id, book);
    }

    @DeleteMapping(ID_URL)
    public ResponseEntity<?> deleteBook(@PathVariable long id){
        return bookService.deleteBook(id);
    }
}
