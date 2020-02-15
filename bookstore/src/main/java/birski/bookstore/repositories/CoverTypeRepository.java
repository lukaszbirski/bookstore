package birski.bookstore.repositories;

import birski.bookstore.models.CoverType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoverTypeRepository extends JpaRepository<CoverType, Long> {

    Optional<CoverType> getCoverTypeByName(String name);

}
