package com.loya.onetomanybidirectionaldemo.PasswordConfirm;

import com.loya.onetomanybidirectionaldemo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserPasswordValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;

        if (user.getPassword().length() <6){
            errors.rejectValue("password", "length", "password must be at least 6 char");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword", "match", "password must match");
        }

    }
}
