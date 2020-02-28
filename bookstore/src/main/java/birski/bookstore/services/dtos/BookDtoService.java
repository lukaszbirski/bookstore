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
    private CategoryRepository categoryRepository;
    private CoverTypeRepository coverTypeRepository;
    private CommentRepository commentRepository;
    private LocalFileService localFileService;
    private BookMapper bookMapper;
    private MapValidationErrorService mapValidationErrorService;

    public BookDtoService(BookRepository bookRepository, CategoryRepository categoryRepository, CoverTypeRepository coverTypeRepository, CommentRepository commentRepository, BookMapper bookMapper, LocalFileService localFileService, MapValidationErrorService mapValidationErrorService) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.coverTypeRepository = coverTypeRepository;
        this.commentRepository = commentRepository;
        this.bookMapper = bookMapper;
        this.localFileService = localFileService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    public ResponseEntity<?> createBookDto(BookDto bookDto, BindingResult bindingResult){
        try{
            ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
            if (errors != null) return errors;
            Book book = bookMapper.reverse(bookDto);
            CoverType coverType = coverTypeRepository.findCoverTypeByName(bookDto.getCoverType());
            logger.info("cover type :" + coverType.toString());
            book.setCoverType(coverType);
            logger.info("cover type from book: " + book.getCoverType().toString());

            Set<Category> categories = new HashSet<>();
            bookDto.getCategories().forEach(c -> {
                Category category = categoryRepository.findCategoryByCategoryName(c);
                logger.info("Category: " + category.toString());
                categories.add(category);
            });
            book.setCategories(categories);
            book.getCategories().stream().forEach(c -> {
                logger.info("Categories from book: " + c.toString());
            });

            bookRepository.save(book);

            //Book result = bookRepository.save(book);

            //logger.info("Book from DB: " + result.toString());
            //BookDto bookDto1 = bookMapper.map(result);

//            Book result = bookRepository.save(bookMapper.reverse(bookDto));
//            if (result == null){
//                logger.error("Error while save book to database!");
//                return null;
//            }
            return new ResponseEntity<BookDto>(bookMapper.map(book), HttpStatus.CREATED);
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

    public ResponseEntity<?> deleteBookDto(String bookTitle){
        return bookRepository.findByTitle(bookTitle).map(b ->{
            bookRepository.delete(b);
            return new ResponseEntity<>("Book with title: " + bookTitle + " was deleted!", HttpStatus.OK);
        }).orElseThrow(()-> new ResourceNotFoundException("Book with title: " + bookTitle + " have not been found."));
    }
}
//todo dokończyć CRUD