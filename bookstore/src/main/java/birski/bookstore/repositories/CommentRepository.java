package birski.bookstore.repositories;

import birski.bookstore.models.Comment;
import birski.bookstore.models.dtos.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    Optional<List<Comment>> getAllByBookTitle(String title);
}
