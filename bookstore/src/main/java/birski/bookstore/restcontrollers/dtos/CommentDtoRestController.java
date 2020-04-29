package birski.bookstore.restcontrollers.dtos;

import birski.bookstore.models.dtos.CommentDto;
import birski.bookstore.services.dtos.CommentDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static birski.bookstore.configs.ApiConfig.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(API_URL + DTO_URL + COMMENTS_URL)
public class CommentDtoRestController {

    private CommentDtoService commentDtoService;

    public CommentDtoRestController(CommentDtoService commentDtoService) {
        this.commentDtoService = commentDtoService;
    }

    @PostMapping("/{name}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createCommentDto(@PathVariable ("name") String bookTitle, @Valid @RequestBody CommentDto commentDto, BindingResult bindingResult){
        return commentDtoService.createCommentDto(commentDto, bindingResult, bookTitle);
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public List<CommentDto> getCommentsDto(){
        return commentDtoService.getCommentsDto();
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasRole('USER')")
    public List<CommentDto> getCommentDto(@PathVariable ("name") String bookTitle){
        return commentDtoService.getCommentsByBookTitle(bookTitle);
    }

    @DeleteMapping("/{name}&{book}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCommentDto(@PathVariable ("name") String author, @PathVariable ("book") String bookTitle){
        return commentDtoService.deleteCommentDto(author, bookTitle);
    }
}
