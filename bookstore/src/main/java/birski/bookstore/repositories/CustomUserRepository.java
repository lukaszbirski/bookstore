package birski.bookstore.repositories;

import birski.bookstore.models.daos.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

    CustomUser findByUsername(String username);
    CustomUser getById(Long id);
}
