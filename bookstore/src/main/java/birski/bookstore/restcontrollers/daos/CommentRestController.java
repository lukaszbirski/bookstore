package birski.bookstore.restcontrollers.daos;

import birski.bookstore.models.daos.Comment;
import birski.bookstore.services.daos.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static birski.bookstore.configs.ApiConfig.API_URL;
import static birski.bookstore.configs.ApiConfig.COMMENTS_URL;

@RestController
@RequestMapping(API_URL + COMMENTS_URL)
public class CommentRestController {

    private CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    public Comment createComment(@RequestBody Comment comment){
        return commentService.createComment(comment);
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable long id){
        return commentService.getCommentById(id);
    }

    @GetMapping()
    public List<Comment> getComments(){
        return commentService.getComments();
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable long id, @RequestBody Comment comment){
        return commentService.updateComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable long id){
        return commentService.deleteComment(id);
    }
}
