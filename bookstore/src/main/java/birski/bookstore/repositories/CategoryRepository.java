package birski.bookstore.repositories;

import birski.bookstore.models.daos.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    Optional<Category> findById(Long aLong);

    Optional<Category> getCategoryByCategoryName(String name);
    Category findCategoryByCategoryName(String name);

}
