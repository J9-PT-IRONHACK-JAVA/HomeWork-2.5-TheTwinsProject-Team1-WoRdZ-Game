package com.ironhack.thetwinsprojectwords.uitls;

import com.ironhack.thetwinsprojectwords.model.Player;
import com.ironhack.thetwinsprojectwords.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InputValidations {

    private final PlayerRepository playerRepository;

    public static boolean validateLettersAndNumbersOnly(String string) {
        return string.matches("^[a-zA-Z1-9_.]{3,10}$");
    }

    public boolean validateExistingName(String name){
        var existingPlayers = playerRepository.findAll();

        List<String> existingNames = new ArrayList<>();

        for (int i = 0; i < existingPlayers.size(); i++) {
            var n = existingPlayers.get(i).getName();
            existingNames.add(n);
        }

        Boolean isExistingName = false;

        for (int j = 0; j < existingNames.size(); j++) {
            if (existingNames.get(j) == name){
                isExistingName = true;
                break;
            }
        }

        return isExistingName;
    }

}
