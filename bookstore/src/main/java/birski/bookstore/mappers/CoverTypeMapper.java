package birski.bookstore.mappers;

import birski.bookstore.models.daos.Book;
import birski.bookstore.models.daos.CoverType;
import birski.bookstore.models.dtos.CoverTypeDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CoverTypeMapper implements Mapper<CoverType, CoverTypeDto>{

    public CoverTypeMapper() {
    }

    @Override
    public CoverTypeDto map(CoverType from) {

        List<String> books = from.getBooks().stream().map(CoverTypeMapper.BooksToString.INSTANCE).collect(Collectors.toList());

        return new CoverTypeDto(from.getName(), books);
    }

    @Override
    public CoverType reverse(CoverTypeDto to) {

        return new CoverType(to.getName(), new HashSet<>());
    }

    private enum BooksToString implements Function<Book, String> {
        INSTANCE;

        @Override
        public String apply(Book book) {
            return book.getTitle();
        }
    }
}