package birski.bookstore.services.dtos;

import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.mappers.BookMapper;
import birski.bookstore.models.dtos.BookDto;
import birski.bookstore.repositories.BookRepository;
import birski.bookstore.repositories.CategoryRepository;
import birski.bookstore.repositories.CommentRepository;
import birski.bookstore.repositories.CoverTypeRepository;
import birski.bookstore.services.daos.LocalFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookDtoService {

    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;
    private CoverTypeRepository coverTypeRepository;
    private CommentRepository commentRepository;
    private LocalFileService localFileService;
    private BookMapper bookMapper;

    public BookDtoService(BookRepository bookRepository, CategoryRepository categoryRepository, CoverTypeRepository coverTypeRepository, CommentRepository commentRepository, BookMapper bookMapper, LocalFileService localFileService) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.coverTypeRepository = coverTypeRepository;
        this.commentRepository = commentRepository;
        this.bookMapper = bookMapper;
        this.localFileService = localFileService;
    }

    public List<BookDto> getBooksDto(){
        List<BookDto> bookDtos = new ArrayList<>();
        bookRepository.findAll().forEach(b -> bookDtos.add(bookMapper.map(b)));
        return bookDtos;
    }

    public BookDto getBookDto(String name){
        return bookRepository.findByTitle(name).map(b -> {
            BookDto bookDto = bookMapper.map(b);
            return bookDto;
        }).orElseThrow(() -> new ResourceNotFoundException("Book name: " + name + " not found"));
    }

    public ResponseEntity<?> deleteBookDto(String bookTitle){
        return bookRepository.findByTitle(bookTitle).map(b ->{
//            for (Comment comment : b.getComments()){
//                commentRepository.delete(comment);
//                //todo musi być jakaś odpowiedź o usunięciu komantarza
//            }
            bookRepository.delete(b);

            //todo zdrobić tablicę rozpensów ponieważ wykonuje się wiele rzeczy i może być potrzeba wiedzieć co dokładnie się wykonało
            return new ResponseEntity<>("Book with title: " + bookTitle + " was deleted!", HttpStatus.OK);
        }).orElseThrow(()-> new ResourceNotFoundException("Book with title: " + bookTitle + " have not been found."));
    }
}
//todo dokończyć CRUD

//    public ResponseEntity<String> deleteFile(String filename)

//return commentRepository.getAllByBookTitle(bookTitle).map( b -> {
//        for (Comment comment : b){
//        if (comment.getAuthor().contains(author)) commentRepository.delete(comment);
//        return new ResponseEntity<String >("Comment authored by:" + author + "regarding book: " + bookTitle + " was deleted!", HttpStatus.OK);
//        }
//        return new ResponseEntity<String>("There is no comment authored by: " + author + " regading book: " + bookTitle, HttpStatus.OK);
//        }).orElseThrow(() -> new ResourceNotFoundException("Book: " + bookTitle + "do not exist!"));