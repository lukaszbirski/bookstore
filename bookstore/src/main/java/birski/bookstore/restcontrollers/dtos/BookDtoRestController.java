package birski.bookstore.restcontrollers.dtos;

import birski.bookstore.models.dtos.BookDto;
import birski.bookstore.models.dtos.CategoryDto;
import birski.bookstore.services.dtos.BookDtoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static birski.bookstore.configs.ApiConfig.*;

@RestController
@RequestMapping(API_URL + DTO_URL + BOOKS_URL)
public class BookDtoRestController {

    private BookDtoService bookDtoService;

    public BookDtoRestController(BookDtoService bookDtoService) {
        this.bookDtoService = bookDtoService;
    }

    @GetMapping()
    public List<BookDto> getBooksDto(){
        return bookDtoService.getBooksDto();
    }
}