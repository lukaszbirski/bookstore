package birski.bookstore.services.validation;

import birski.bookstore.models.daos.CustomUser;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return CustomUser.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        CustomUser customUser = (CustomUser) object;
        if (customUser.getPassword().length() < 6 ) errors.rejectValue("password", "Lenght", "Password must be at least 6 characters");
        if (!customUser.getPassword().equals(customUser.getConfirmPassword())) errors.rejectValue("confirmPassword", "Match", "Password must match");
    }
}
