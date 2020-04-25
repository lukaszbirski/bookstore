package birski.bookstore.services.dtos;

import birski.bookstore.exceptions.UsernameAlreadyExistsException;
import birski.bookstore.mappers.CustomUserMapper;
import birski.bookstore.models.daos.CustomUser;
import birski.bookstore.models.dtos.CustomUserDto;
import birski.bookstore.repositories.CustomUserRepository;
import birski.bookstore.security.JwtTokenProvider;
import birski.bookstore.security.payload.JWTLoginSuccessResponse;
import birski.bookstore.security.payload.LoginRequest;
import birski.bookstore.services.validation.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

import static birski.bookstore.configs.SecurityConstants.TOKEN_PREFIX;

@Service
public class CustomUserDtoService {

    private CustomUserRepository customUserRepository;
    private CustomUserMapper customUserMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MapValidationErrorService mapValidationErrorService;

    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;

    public CustomUserDtoService(CustomUserRepository customUserRepository, CustomUserMapper customUserMapper, BCryptPasswordEncoder bCryptPasswordEncoder, MapValidationErrorService mapValidationErrorService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.customUserRepository = customUserRepository;
        this.customUserMapper = customUserMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapValidationErrorService = mapValidationErrorService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }


    public ResponseEntity<?> saveCustomUserDto(CustomUserDto customUserDto, BindingResult bindingResult) {
        try{
            ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
            if (errors != null) return errors;
            if (!customUserDto.getPassword().equals(customUserDto.getConfirmPassword())) {

                Map<String, String> error = new HashMap<>();
                error.put("password", "Passwords do not match");
                return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
            }

            if (customUserDto.getPassword() != null)  customUserDto.setPassword(bCryptPasswordEncoder.encode(customUserDto.getPassword()));
            customUserDto.setConfirmPassword("");
            CustomUser result = customUserRepository.save(customUserMapper.reverse(customUserDto));
            return new ResponseEntity<CustomUserDto>(customUserMapper.map(result), HttpStatus.CREATED);
        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Email " + customUserDto.getUsername() + " already exists in database");
        }

    }
//todo ta część została stworzona dla CustomUserDto
    public ResponseEntity<?> getAuthentication(LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
        if (errors != null) return errors;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
    }
}
