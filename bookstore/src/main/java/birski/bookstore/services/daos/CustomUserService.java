package birski.bookstore.services.daos;

import birski.bookstore.exceptions.UsernameAlreadyExistsException;
import birski.bookstore.models.daos.CustomUser;
import birski.bookstore.models.daos.Role;
import birski.bookstore.repositories.CustomUserRepository;
import birski.bookstore.repositories.RoleRepository;
import birski.bookstore.services.validation.MapValidationErrorService;
import birski.bookstore.services.validation.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserService.class);

    private CustomUserRepository customUserRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MapValidationErrorService mapValidationErrorService;
    private UserValidator userValidator;

    public CustomUserService(CustomUserRepository customUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MapValidationErrorService mapValidationErrorService, UserValidator userValidator, RoleRepository roleRepository) {
        this.customUserRepository = customUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapValidationErrorService = mapValidationErrorService;
        this.userValidator = userValidator;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<?> saveCustomUser (CustomUser customUser, BindingResult bindingResult) {

        try{
            userValidator.validate(customUser, bindingResult);
            if (customUser.getPassword() != null)  customUser.setPassword(bCryptPasswordEncoder.encode(customUser.getPassword()));
            ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
           if (errors != null) return errors;
            logger.info("BEFORE CONFIRM PASS IS REMOVED");
            customUser.setConfirmPassword("");
            logger.info("BEFORE COLLECTING ROLE");

            Role role = roleRepository.findByRoleName("USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            customUser.setRoles(roles);

            logger.info("MY USER: " + customUser.toString());

            CustomUser result = customUserRepository.save(customUser);
            return new ResponseEntity<CustomUser>(result, HttpStatus.CREATED);
        }catch (Exception e){
           throw  new UsernameAlreadyExistsException("Email " + customUser.getUsername() + " already exists in database");
        }

    }


}
