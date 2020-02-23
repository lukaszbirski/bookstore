package birski.bookstore.services.daos;

import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.models.daos.Book;
import birski.bookstore.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Scope(proxyMode = ScopedProxyMode.INTERFACES)
//@Transactional
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public Book getBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book id: " + id + " not found."));
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book updateBook(long id, Book book){
        return bookRepository.findById(id).map(b -> {
            b.setTitle(book.getTitle());
            b.setAuthor(book.getAuthor());
            b.setCategories(book.getCategories());
            b.setCoverType(book.getCoverType());
            b.setPublisher(book.getPublisher());
            b.setDescription(book.getDescription());
            b.setEan(book.getEan());
            b.setPages(book.getPages());
            b.setPrice(book.getPrice());
            b.setReleaseDate(book.getReleaseDate());

            b.setComments(book.getComments());
            return bookRepository.save(b);
        }).orElseThrow(() -> new ResourceNotFoundException("Book id: " + id + " not found."));
    }

        public ResponseEntity<?> deleteBook(long id){
        return bookRepository.findById(id).map(b ->{
            //bookRepository.deleteById(id);
            //bookRepository.delete(b);
            bookRepository.deleteBookById(id);
            return new ResponseEntity<>("Book id: " + b.getTitle() + " was deleted!", HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("Book id: " + id + " not found."));
    }
//    public Book deleteBook(long id){
//        return bookRepository.findById(id).map(b->{
//                bookRepository.deleteById(id);
//                return book
//        }).

//    }
}

//    public ResponseEntity<?> deleteBook(long id){
//        return bookRepository.findById(id).map(b ->{
//            bookRepository.delete(b);
//            return new ResponseEntity<>("Book id: " + id + " was deleted!", HttpStatus.OK);
//        }).orElseThrow(() -> new ResourceNotFoundException("Book id: " + id + " not found."));
//    }