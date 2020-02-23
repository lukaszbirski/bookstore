package birski.bookstore.services.dtos;

import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.mappers.CommentMapper;
import birski.bookstore.models.daos.Comment;
import birski.bookstore.models.dtos.CommentDto;
import birski.bookstore.repositories.CommentRepository;
import birski.bookstore.services.validation.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentDtoService {

    private CommentRepository commentRepository;
    private CommentMapper commentMapper;
    private MapValidationErrorService mapValidationErrorService;

    public CommentDtoService(CommentRepository commentRepository, CommentMapper commentMapper, MapValidationErrorService mapValidationErrorService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    public ResponseEntity<?> createCommentDto(CommentDto commentDto, BindingResult bindingResult){
        ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
        if (errors != null) return errors;
        commentRepository.save(commentMapper.reverse(commentDto));
        return new ResponseEntity<CommentDto>(commentDto, HttpStatus.CREATED);
    }

    public List<CommentDto> getCommentsDto (){
        List<CommentDto> commentDtos = new ArrayList<>();
        commentRepository.findAll().forEach( c -> commentDtos.add(commentMapper.map(c)));
        return commentDtos;
    }

    public List<CommentDto> getCommentsByBookTitle(String title){
        List<CommentDto> commentDtos = new ArrayList<>();
        return commentRepository.getAllByBookTitle(title).map(c -> {
            for (Comment comment : c){
                commentDtos.add(commentMapper.map(comment));
            }
            return commentDtos;
        }).orElseThrow(() -> new ResourceNotFoundException("No books have been found for book title: " + title));
    }

    public ResponseEntity<?> deleteCommentDto(String author, String bookTitle){
        return commentRepository.getAllByBookTitle(bookTitle).map( b -> {
            for (Comment comment : b){
                if (comment.getAuthor().equals(author)) {
                    commentRepository.delete(comment);

                    return new ResponseEntity<String>("Comment authored by: " + author + " for book: " + bookTitle + " was deleted!", HttpStatus.OK);
                }
            }
            return new ResponseEntity<String>("There is no comment authored by: " + author + " regarding book: " + bookTitle, HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("Book: " + bookTitle + "do not exist!"));
    }

}
