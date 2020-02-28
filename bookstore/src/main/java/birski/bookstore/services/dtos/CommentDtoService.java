package birski.bookstore.services.dtos;

import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.mappers.CommentMapper;
import birski.bookstore.models.daos.Book;
import birski.bookstore.models.daos.Comment;
import birski.bookstore.models.dtos.CommentDto;
import birski.bookstore.repositories.BookRepository;
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
    private BookRepository bookRepository;

    public CommentDtoService(CommentRepository commentRepository, CommentMapper commentMapper, MapValidationErrorService mapValidationErrorService, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.mapValidationErrorService = mapValidationErrorService;
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<?> createCommentDto(CommentDto commentDto, BindingResult bindingResult, String bookTitle){
        ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
        if (errors != null) return errors;
        Book book = bookRepository.getBookByTitle(bookTitle);
        Comment comment = commentMapper.reverse(commentDto);
        comment.setBook(book);
        commentRepository.save(comment);
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
//todo stworzyć updatowanie komentarza
}
