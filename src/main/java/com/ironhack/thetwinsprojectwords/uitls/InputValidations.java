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
        List<Player> existingPlayers = playerRepository.findAll();

        List<String> existingNames = new ArrayList<>();
        for (int i = 0; i < existingPlayers.size(); i++) {
            existingNames.add(existingPlayers.get(i).getName());
        }

        Boolean isExistingName = false;

        for (String existingName : existingNames){
            if (existingName == name){
                isExistingName = true;
                break;
            }
        }

        return isExistingName;
    }

}
