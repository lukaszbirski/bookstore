package birski.bookstore.services.daos;

import birski.bookstore.exceptions.UsernameAlreadyExistsException;
import birski.bookstore.models.daos.CustomUser;
import birski.bookstore.repositories.CustomUserRepository;
import birski.bookstore.services.validation.MapValidationErrorService;
import birski.bookstore.services.validation.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class CustomUserService {

    private CustomUserRepository customUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MapValidationErrorService mapValidationErrorService;
    private UserValidator userValidator;

    public CustomUserService(CustomUserRepository customUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MapValidationErrorService mapValidationErrorService, UserValidator userValidator) {
        this.customUserRepository = customUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapValidationErrorService = mapValidationErrorService;
        this.userValidator = userValidator;
    }

    public ResponseEntity<?> saveCustomUser (CustomUser customUser, BindingResult bindingResult) {

        try{
            userValidator.validate(customUser, bindingResult);
            if (customUser.getPassword() != null)  customUser.setPassword(bCryptPasswordEncoder.encode(customUser.getPassword()));
            ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
            if (errors != null) return errors;
            
            customUser.setConfirmPassword("");
            CustomUser result = customUserRepository.save(customUser);
            return new ResponseEntity<CustomUser>(result, HttpStatus.CREATED);
        }catch (Exception e){
            throw  new UsernameAlreadyExistsException("Email " + customUser.getUsername() + " already exists in database");
        }

    }


}
