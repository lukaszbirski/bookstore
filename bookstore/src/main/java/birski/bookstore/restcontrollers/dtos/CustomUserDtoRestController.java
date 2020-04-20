package birski.bookstore.restcontrollers.dtos;

import birski.bookstore.models.daos.CustomUser;
import birski.bookstore.models.dtos.CustomUserDto;
import birski.bookstore.services.dtos.CustomUserDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static birski.bookstore.configs.ApiConfig.*;

@RestController
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

}
