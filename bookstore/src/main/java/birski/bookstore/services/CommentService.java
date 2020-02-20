package birski.bookstore.services;

import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.models.Comment;
import birski.bookstore.repositories.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment){
        return commentRepository.save(comment);
    }

    public Comment getCommentById(long id){
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment id: " + id + " not found."));
    }

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    public Comment updateComment(long id, Comment comment){
        return commentRepository.findById(id).map(c -> {
            c.setAuthor(comment.getAuthor());
            c.setDescription(comment.getDescription());
            c.setRating(comment.getRating());
            c.setDate(comment.getDate());
            return commentRepository.save(c);
        }).orElseThrow(()-> new ResourceNotFoundException("Comment id: " + id + " not found."));
    }

    public ResponseEntity<?> deleteComment(long id){
        return commentRepository.findById(id).map(c -> {
            commentRepository.deleteById(id);
            return new ResponseEntity<>("Comment id: " + id + " was deleted!", HttpStatus.OK);
        }).orElseThrow(()-> new ResourceNotFoundException("Comment id: " + id + " not found."));
    }
}