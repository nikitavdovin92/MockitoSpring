package com.example.employeebook.validatorService;

import com.example.employeebook.exception.IncorrectFirstnameException;
import com.example.employeebook.exception.IncorrectLastnameException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {
    public static String validateName(String name) {
        if (!StringUtils.isAlpha(name)) {
        throw new IncorrectFirstnameException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }

    public static String validateSurname(String surName) {
        String[] surNames = surName.split("-");
        for (int i = 0; i < surNames.length; i++) {
            if (!StringUtils.isAlpha(surNames[i])) {
                throw new IncorrectLastnameException();
            }
            surNames[i] = StringUtils.capitalize(surNames[i].toLowerCase());
        } return String.join("-", surNames);

    }
}
