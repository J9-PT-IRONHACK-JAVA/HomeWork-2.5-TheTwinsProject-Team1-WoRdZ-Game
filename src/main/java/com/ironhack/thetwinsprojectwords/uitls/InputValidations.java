package com.ironhack.thetwinsprojectwords.uitls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InputValidations {

    public static boolean validateLettersAndNumbersOnly(String string) {
        return string.matches("^[A-Z0-9]+$");
    }

}
