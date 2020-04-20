package birski.bookstore.services.dtos;

import birski.bookstore.exceptions.UsernameAlreadyExistsException;
import birski.bookstore.mappers.CustomUserMapper;
import birski.bookstore.models.daos.CustomUser;
import birski.bookstore.models.dtos.CustomUserDto;
import birski.bookstore.repositories.CustomUserRepository;
import birski.bookstore.services.validation.CustomUserDtoValidator;
import birski.bookstore.services.validation.MapValidationErrorService;
import birski.bookstore.services.validation.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class CustomUserDtoService {

    private CustomUserRepository customUserRepository;
    private CustomUserMapper customUserMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MapValidationErrorService mapValidationErrorService;
    private CustomUserDtoValidator customUserDtoValidator;

    public CustomUserDtoService(CustomUserRepository customUserRepository, CustomUserMapper customUserMapper, BCryptPasswordEncoder bCryptPasswordEncoder, MapValidationErrorService mapValidationErrorService, CustomUserDtoValidator customUserDtoValidator) {
        this.customUserRepository = customUserRepository;
        this.customUserMapper = customUserMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapValidationErrorService = mapValidationErrorService;
        this.customUserDtoValidator = customUserDtoValidator;
    }


    public ResponseEntity<?> saveCustomUserDto(CustomUserDto customUserDto, BindingResult bindingResult) {

        try{
            customUserDtoValidator.validate(customUserDto, bindingResult);
            if (customUserDto.getPassword() != null)  customUserDto.setPassword(bCryptPasswordEncoder.encode(customUserDto.getPassword()));
            ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
            if (errors != null) return errors;

            customUserDto.setConfirmPassword("");

            CustomUser result = customUserRepository.save(customUserMapper.reverse(customUserDto));
            return new ResponseEntity<CustomUserDto>(customUserMapper.map(result), HttpStatus.CREATED);
        }catch (Exception e){
            throw  new UsernameAlreadyExistsException("Email " + customUserDto.getUsername() + " already exists in database");
        }

    }
}
