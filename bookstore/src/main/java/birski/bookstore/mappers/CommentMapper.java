package birski.bookstore.mappers;

import birski.bookstore.models.daos.Book;
import birski.bookstore.models.daos.Comment;
import birski.bookstore.models.dtos.CommentDto;
import birski.bookstore.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CommentMapper implements Mapper<Comment, CommentDto>{

    private static final Logger logger = LoggerFactory.getLogger(CommentMapper.class);

    public CommentMapper() {
    }

    @Override
    public CommentDto map(Comment from) {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthor(from.getAuthor());
        commentDto.setDescription(from.getDescription());
        commentDto.setRating(from.getRating());
        commentDto.setDate(from.getDate().toString().substring(0,10));
        commentDto.setBookTitle(from.getBook().getTitle());
        return commentDto;
    }

    @Override
    public Comment reverse(CommentDto to) {
        Comment comment = new Comment();
        comment.setAuthor(to.getAuthor());
        comment.setDescription(to.getDescription());
        comment.setRating(to.getRating());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(to.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        comment.setDate(date);
        return comment;
    }
}
