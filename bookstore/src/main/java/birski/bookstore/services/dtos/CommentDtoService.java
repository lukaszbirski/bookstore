package birski.bookstore.services.dtos;

import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.mappers.CommentMapper;
import birski.bookstore.models.Comment;
import birski.bookstore.models.dtos.CategoryDto;
import birski.bookstore.models.dtos.CommentDto;
import birski.bookstore.models.dtos.CoverTypeDto;
import birski.bookstore.repositories.CommentRepository;
import birski.bookstore.services.validation.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


    public List<CommentDto> getCommentsDto (){
        List<CommentDto> commentDtos = new ArrayList<>();
        commentRepository.findAll().forEach( c -> commentDtos.add(commentMapper.map(c)));
        return commentDtos;
    }

//    List<CoverTypeDto> coverTypeDtos = new ArrayList<>();
//        coverTypeRepository.findAll().forEach(c -> coverTypeDtos.add(coverTypeMapper.map(c)));
//        return coverTypeDtos;
    //todo naprawić pobieranie komentarzy po tytule książki
    public List<CommentDto> getCommentsByBookTitle(String title){
        List<CommentDto> commentDtos = new ArrayList<>();
        //List<Comment> comments = commentRepository.getAllByBookTitle(title);
//        if (comments != null) return new ResponseEntity<String>("No comments have been found for book title: " + title + ".", HttpStatus.OK);
//            for (Comment c : comments) {
//                System.out.println(c);
//                commentDtos.add(commentMapper.map(c));
//            }
//        return new ResponseEntity<List<CommentDto>>(commentDtos, HttpStatus.OK);
        return commentRepository.getAllByBookTitle(title).map(c -> {
            for (Comment comment : c){
                commentDtos.add(commentMapper.map(comment));
            }
            return commentDtos;
        }).orElseThrow(() -> new ResourceNotFoundException("No books have been found for book title: " + title));
    }

}
