package birski.bookstore.repositories;

import birski.bookstore.models.daos.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    Optional<Category> findById(Long aLong);

    //@Query("select c from Category c where c.categoryName=?1")
    //Category getCategoryByCategoryName(String categoryName);

    Optional<Category> getCategoryByCategoryName(String name);
}
