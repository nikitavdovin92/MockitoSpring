package com.example.employeebook.validatorService;
import com.example.employeebook.exception.IncorrectFirstnameException;
import com.example.employeebook.exception.IncorrectLastnameException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {
    public static String validateFirstName(String firstName) {
        if (!StringUtils.isAlpha(firstName)) {
        throw new IncorrectFirstnameException();
        }
        return StringUtils.capitalize(firstName.toLowerCase());
    }

    public static String validateLastName(String lastName) {
        String[] lastnames = lastName.split("-");
        for (int i = 0; i < lastnames.length; i++) {
            if (!StringUtils.isAlpha(lastnames[i])) {
                throw new IncorrectLastnameException();
            }
            lastnames[i] = StringUtils.capitalize(lastnames[i].toLowerCase());
        } return String.join("-", lastnames);

    }
}
