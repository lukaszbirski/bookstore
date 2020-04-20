package birski.bookstore.services.daos;

import birski.bookstore.models.daos.CustomUser;
import birski.bookstore.repositories.CustomUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private CustomUserRepository customUserRepository;

    public CustomUserDetailsService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        CustomUser customUser = customUserRepository.findByUsername(s);
        if (customUser == null) new UsernameNotFoundException("User not found");
        return customUser;
    }

    @Transactional
    public CustomUser loadCustomUserById(Long id){
        CustomUser customUser = customUserRepository.getById(id);
        if (customUser == null) new UsernameNotFoundException("User not found");
        return customUser;
    }
}
