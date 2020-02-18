package birski.bookstore.services.dtos;

import birski.bookstore.mappers.BookMapper;
import birski.bookstore.models.dtos.BookDto;
import birski.bookstore.repositories.BookRepository;
import birski.bookstore.repositories.CategoryRepository;
import birski.bookstore.repositories.CommentRepository;
import birski.bookstore.repositories.CoverTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookDtoService {

    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;
    private CoverTypeRepository coverTypeRepository;
    private CommentRepository commentRepository;
    private BookMapper bookMapper;

    public BookDtoService(BookRepository bookRepository, CategoryRepository categoryRepository, CoverTypeRepository coverTypeRepository, CommentRepository commentRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.coverTypeRepository = coverTypeRepository;
        this.commentRepository = commentRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getBooksDto(){
        List<BookDto> bookDtos = new ArrayList<>();
        bookRepository.findAll().forEach(b -> bookDtos.add(bookMapper.map(b)));
        return bookDtos;
    }


}
