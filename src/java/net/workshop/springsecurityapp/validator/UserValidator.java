package net.workshop.springsecurityapp.validator;

import com.sun.xml.internal.bind.v2.TODO;
import net.workshop.springsecurityapp.entity.User;
import net.workshop.springsecurityapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link net.workshop.springsecurityapp.entity.User} class
 * implements {@link Validator} interface
 *
 * @author Andrii Androsiuk
 * @version 1.0
 *
 * */

@Component
public class UserValidator implements Validator{

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "useremail", "Required");
        if (user.getUseremail().length() < 8 || user.getUseremail().length() > 32){
            errors.rejectValue("emailname", "Size.userForm.code");
        }

        if (userService.findByEmail(user.getUseremail())!= null){
            errors.rejectValue("emailname","Duplicate.userForm.code");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32){
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (user.getConfirmPassword().equals(user.getPassword())){
            errors.rejectValue("confirmPassword","Different.userForm.password");
        }

        // TODO: add method for connection with User.code and matching it.
        // TODO: add strings to validator.properties
    }
}
