package birski.bookstore.mappers;

import birski.bookstore.models.Book;
import birski.bookstore.models.Category;
import birski.bookstore.models.dtos.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {

    @Override
    public CategoryDto map(Category from) {

        List<String> books = from.getBooks().stream().map(BooksToString.INSTANCE).collect(Collectors.toList());

        return new CategoryDto(from.getCategoryName(), books);
    }

    @Override
    public Category reverse(CategoryDto to) {

        return new Category(to.getCategoryName(), new HashSet<>());
    }

    private enum BooksToString implements Function<Book, String>{
        INSTANCE;

        @Override
        public String apply(Book book) {
            return book.getTitle();
        }
    }
}