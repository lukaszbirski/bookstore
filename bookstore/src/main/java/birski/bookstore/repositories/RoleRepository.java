package birski.bookstore.repositories;

import birski.bookstore.models.daos.CustomUser;
import birski.bookstore.models.daos.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String name);
    Set<Role> getRolesByUsers(CustomUser c);
}
