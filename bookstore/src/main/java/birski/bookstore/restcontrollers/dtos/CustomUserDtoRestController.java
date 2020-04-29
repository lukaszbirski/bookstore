package birski.bookstore.restcontrollers.dtos;

import birski.bookstore.models.daos.CustomUser;
import birski.bookstore.models.dtos.CustomUserDto;
import birski.bookstore.security.JwtTokenProvider;
import birski.bookstore.security.payload.LoginRequest;
import birski.bookstore.services.dtos.CustomUserDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static birski.bookstore.configs.ApiConfig.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(API_URL + DTO_URL + USERS_URL)
public class CustomUserDtoRestController {

    private CustomUserDtoService customUserDtoService;

    public CustomUserDtoRestController(CustomUserDtoService customUserDtoService) {
        this.customUserDtoService = customUserDtoService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomUserDto(@Valid @RequestBody CustomUserDto customUserDto, BindingResult bindingResult){
        return customUserDtoService.saveCustomUserDto(customUserDto, bindingResult);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateCustomUserDto(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        return customUserDtoService.getAuthentication(loginRequest, bindingResult);
    }

}
