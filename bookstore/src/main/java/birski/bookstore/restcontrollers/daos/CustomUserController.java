package birski.bookstore.restcontrollers.daos;

import birski.bookstore.models.daos.CustomUser;
import birski.bookstore.services.daos.CustomUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static birski.bookstore.configs.ApiConfig.API_URL;
import static birski.bookstore.configs.ApiConfig.USERS_URL;

@RestController
@RequestMapping(API_URL + USERS_URL)
public class CustomUserController {

    private CustomUserService customUserService;

    public CustomUserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomUser(@Valid @RequestBody CustomUser customUser, BindingResult bindingResult){
        return customUserService.saveCustomUser(customUser, bindingResult);
    }


}
