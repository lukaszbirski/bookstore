package birski.bookstore.services.dtos;

import birski.bookstore.exceptions.NameException;
import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.mappers.BookMapper;
import birski.bookstore.models.daos.Book;
import birski.bookstore.models.daos.Category;
import birski.bookstore.models.daos.CoverType;
import birski.bookstore.models.dtos.BookDto;
import birski.bookstore.repositories.BookRepository;
import birski.bookstore.repositories.CategoryRepository;
import birski.bookstore.repositories.CommentRepository;
import birski.bookstore.repositories.CoverTypeRepository;
import birski.bookstore.services.daos.LocalFileService;
import birski.bookstore.services.validation.MapValidationErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BookDtoService {

    private static final Logger logger = LoggerFactory.getLogger(BookDtoService.class);

    private BookRepository bookRepository;
    private LocalFileService localFileService;
    private BookMapper bookMapper;
    private MapValidationErrorService mapValidationErrorService;

    public BookDtoService(BookRepository bookRepository, BookMapper bookMapper, LocalFileService localFileService, MapValidationErrorService mapValidationErrorService) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.localFileService = localFileService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    public ResponseEntity<?> createBookDto(BookDto bookDto, BindingResult bindingResult){
        try{
            ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
            if (errors != null) return errors;
            Book book = bookMapper.reverse(bookDto);
            Book result = bookRepository.save(book);
            return new ResponseEntity<BookDto>(bookMapper.map(result), HttpStatus.CREATED);
        }catch (Exception e){
            throw new NameException("Book name " + bookDto.getTitle() + " already exists");
        }
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

    //todo dokończyć updateBook
    public ResponseEntity<?> updateBookDto(){
        return null;
    }

    public ResponseEntity<?> deleteBookDto(String bookTitle){
        return bookRepository.findByTitle(bookTitle).map(b ->{
            bookRepository.delete(b);
            return new ResponseEntity<>("Book with title: " + bookTitle + " was deleted!", HttpStatus.OK);
        }).orElseThrow(()-> new ResourceNotFoundException("Book with title: " + bookTitle + " have not been found."));
    }
}
