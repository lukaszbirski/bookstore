package birski.bookstore.restcontrollers.dtos;

import birski.bookstore.models.dtos.BookDto;
import birski.bookstore.models.dtos.CategoryDto;
import birski.bookstore.services.dtos.BookDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static birski.bookstore.configs.ApiConfig.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(API_URL + DTO_URL + BOOKS_URL)
public class BookDtoRestController {

    private BookDtoService bookDtoService;

    public BookDtoRestController(BookDtoService bookDtoService) {
        this.bookDtoService = bookDtoService;
    }

    @PostMapping()
    public ResponseEntity<?> createBookDto(@Valid @RequestBody BookDto bookDto, BindingResult bindingResult){
        return bookDtoService.createBookDto(bookDto, bindingResult);
    }

    @GetMapping()
    public List<BookDto> getBooksDto(){
        return bookDtoService.getBooksDto();
    }

    @GetMapping(NAME_URL)
    public BookDto getBookDto(@PathVariable String name){
        return bookDtoService.getBookDto(name);
    }

    @GetMapping("/category/{category}")
        public List<BookDto> getBooksDtoByCategory(@PathVariable ("category") String category){
        return bookDtoService.getBooksDtoByCategory(category);
    }


    @PutMapping("/{name}")
    public ResponseEntity<?> updateBookDto(@PathVariable ("name") String bookName, @Valid @RequestBody BookDto bookDto, BindingResult bindingResult){
        return bookDtoService.updateBookDto(bookName, bookDto, bindingResult);
    }

    @DeleteMapping(NAME_URL)
    public ResponseEntity<?> deleteBookDto(@PathVariable("name") String bookTitle){
        return bookDtoService.deleteBookDto(bookTitle);
    }
}




