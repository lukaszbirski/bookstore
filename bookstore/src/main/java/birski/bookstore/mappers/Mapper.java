package birski.bookstore.mappers;

public interface Mapper<F, T> {

    T map(F from);

    F reverse(T to);
}
