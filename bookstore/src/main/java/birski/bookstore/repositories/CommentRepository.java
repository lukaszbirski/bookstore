package birski.bookstore.repositories;

import birski.bookstore.models.daos.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<List<Comment>> getAllByBookTitle(String title);
    Set<Comment> findAllByBookTitle(String title);

    @Override
    void delete(Comment comment);
}
