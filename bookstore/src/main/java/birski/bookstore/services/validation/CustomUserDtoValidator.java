package birski.bookstore.services.validation;

import birski.bookstore.models.dtos.CustomUserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomUserDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomUserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        CustomUserDto customUserDto = (CustomUserDto) object;
        if (customUserDto.getPassword().length() < 6 ) errors.rejectValue("password", "Lenght", "Password must be at least 6 characters");
        if (!customUserDto.getPassword().equals(customUserDto.getConfirmPassword())) errors.rejectValue("confirmPassword", "Match", "Password must match");
    }
}
