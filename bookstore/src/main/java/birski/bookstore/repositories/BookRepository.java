package birski.bookstore.repositories;

import birski.bookstore.models.daos.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
    Book getBookByTitle(String title);

    @Transactional
    @Modifying
    @Query("delete from Book b where b.id = :id")
    void deleteBookById(@Param("id") Long id);

//    @Query("select c from Car c where c.id=?1")
//    Car getCarById(long id);

}
