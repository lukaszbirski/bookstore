package birski.bookstore.restcontrollers.dtos;

import birski.bookstore.models.dtos.CommentDto;
import birski.bookstore.services.dtos.CommentDtoService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createCommentDto(@PathVariable ("name") String bookTitle, @Valid @RequestBody CommentDto commentDto, BindingResult bindingResult){
        return commentDtoService.createCommentDto(commentDto, bindingResult, bookTitle);
    }

    @GetMapping()
    public List<CommentDto> getCommentsDto(){
        return commentDtoService.getCommentsDto();
    }

    @GetMapping("/{name}")
    public List<CommentDto> getCommentDto(@PathVariable ("name") String bookTitle){
        return commentDtoService.getCommentsByBookTitle(bookTitle);
    }

    @DeleteMapping("/{name}&{book}")
    public ResponseEntity<?> deleteCommentDto(@PathVariable ("name") String author, @PathVariable ("book") String bookTitle){
        return commentDtoService.deleteCommentDto(author, bookTitle);
    }

//    @PostMapping()
//    public ResponseEntity<?> createCommentDto(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult){
//        return commentDtoService.createCommentDto(commentDto, bindingResult);
//    }
//
//    @PutMapping(NAME_URL)
//    public ResponseEntity<?> updateCommentDto(@PathVariable ("title") String title, @Valid @RequestBody CommentDto commentDto, BindingResult bindingResult){
//        return commentDtoService.updateCommentDto(title, commentDto, bindingResult); //todo zastanowić się czy na pewno po tytule chcemu móc update'ować komentarz
//    }

}



//
//    @DeleteMapping(NAME_URL)
//    public ResponseEntity<?> deleteCategoryDto(@PathVariable ("name") String categoryName){
//        return categoryDtoService.deleteCategoryDto(categoryName);
//    }
