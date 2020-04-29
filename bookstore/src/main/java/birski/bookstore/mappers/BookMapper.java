package birski.bookstore.mappers;

import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.models.daos.Book;
import birski.bookstore.models.daos.Category;
import birski.bookstore.models.daos.Comment;
import birski.bookstore.models.daos.CoverType;
import birski.bookstore.models.dtos.BookDto;
import birski.bookstore.models.dtos.CategoryDto;
import birski.bookstore.models.dtos.CommentDto;
import birski.bookstore.models.dtos.CoverTypeDto;
import birski.bookstore.repositories.CategoryRepository;
import birski.bookstore.repositories.CommentRepository;
import birski.bookstore.repositories.CoverTypeRepository;
import birski.bookstore.services.daos.CoverTypeService;
import birski.bookstore.services.dtos.CoverTypeDtoService;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class BookMapper implements Mapper<Book, BookDto>{

    private static final Logger logger = LoggerFactory.getLogger(BookMapper.class);

    private CommentMapper commentMapper;
    private CoverTypeRepository coverTypeRepository;
    private CategoryRepository categoryRepository;
    private CommentRepository commentRepository;

    public BookMapper(CommentMapper commentMapper, CoverTypeRepository coverTypeRepository, CategoryRepository categoryRepository, CommentRepository commentRepository) {
        this.commentMapper = commentMapper;
        this.coverTypeRepository = coverTypeRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
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

    @Override
    public Book reverse(BookDto to) {
        Book book = new Book();
        book.setAuthor(to.getAuthor());
        //logger.info("Author: " + book.getAuthor());
        book.setTitle(to.getTitle());
        //logger.info("Title: " + book.getTitle());
        book.setPublisher(to.getPublisher());
        //logger.info("Publisher: " + book.getPublisher());
        book.setDescription(to.getDescription());
        //logger.info("Description: " + book.getDescription());
        book.setEan(to.getEan());
        //logger.info("Ean:" + book.getEan());
        book.setPrice(to.getPrice());
        //logger.info("Price: " + book.getPrice());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(to.getReleaseDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setReleaseDate(date);
        //logger.info("Date: " + book.getReleaseDate());
        book.setPages(to.getPages());
        //logger.info("Pages: " + book.getPages());
        book.setFileName(to.getFileName());
        //logger.info("FileName: " + book.getFileName());

        CoverType coverType = coverTypeRepository.findCoverTypeByName(to.getCoverType());
        //logger.info("cover type :" + coverType.toString());
        book.setCoverType(coverType);
        //logger.info("cover type from book: " + book.getCoverType().toString());

        Set<Category> categories = new HashSet<>();
        to.getCategories().forEach(c -> {
            Category category = categoryRepository.findCategoryByCategoryName(c);
            //logger.info("Category: " + category.toString());
            categories.add(category);
        });
        book.setCategories(categories);
//        book.getCategories().stream().forEach(c -> {
//            //logger.info("Categories from book: " + c.toString());
//        });
        Set<Comment> comments = new HashSet<>();
        comments = commentRepository.findAllByBookTitle(to.getTitle());
        book.setComments(comments);
//        book.getComments().stream().forEach(c -> {
//            //logger.info("Comment: " + c.toString());
//        });

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
