package birski.bookstore.mappers;

import birski.bookstore.models.daos.Book;
import birski.bookstore.models.daos.Category;
import birski.bookstore.models.dtos.BookDto;
import birski.bookstore.models.dtos.CommentDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class BookMapper implements Mapper<Book, BookDto>{

    private CommentMapper commentMapper;

    public BookMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public BookDto map(Book from) {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor(from.getAuthor());
        bookDto.setTitle(from.getTitle());
        List<String> categories = from.getCategories().stream().map(CategoriesToString.INSTANCE).collect(Collectors.toList());
        bookDto.setCategories(categories);
        bookDto.setCoverType(from.getCoverType().getName());
        bookDto.setPublisher(from.getPublisher());
        bookDto.setDescription(from.getDescription());
        bookDto.setEan(from.getEan());
        bookDto.setPrice(from.getPrice());
        bookDto.setReleaseDate(from.getReleaseDate().toString().substring(0,10));
        bookDto.setPages(from.getPages());
        bookDto.setFileName(from.getFileName());
        List<CommentDto> commentDtos = new ArrayList<>();
        from.getComments().stream().forEach(c -> {
            commentDtos.add(commentMapper.map(c));
        });
        bookDto.setComments(commentDtos);
        return bookDto;
    }
    //todo wykonać reversMapper dla Book
    @Override
    public Book reverse(BookDto to) {
        Book book = new Book();
        book.setAuthor(to.getAuthor());
        book.setTitle(to.getTitle());
        return book;
    }

    private enum CategoriesToString implements Function<Category, String>{
        INSTANCE;

        @Override
        public String apply(Category category) {
            return category.getCategoryName();
        }
    }
}
