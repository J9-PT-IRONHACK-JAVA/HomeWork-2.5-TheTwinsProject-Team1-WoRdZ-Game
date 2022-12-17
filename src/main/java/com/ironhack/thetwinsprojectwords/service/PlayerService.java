package com.ironhack.thetwinsprojectwords.service;

import com.ironhack.thetwinsprojectwords.model.Player;
import com.ironhack.thetwinsprojectwords.repository.PlayerRepository;
import com.ironhack.thetwinsprojectwords.uitls.ConsoleColors;
import com.ironhack.thetwinsprojectwords.uitls.InputValidations;
import com.ironhack.thetwinsprojectwords.uitls.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final InputValidations inputValidations;
    private final Utils utils;
    private final Scanner scanner;

    public Player selectExistingPlayer(){
        utils.printWithColor("\nSee below the list of existing users... ", ConsoleColors.BLUE);

        showExistingPlayers();

        utils.printWithColor("\nPlease, select the desired player by introducing their corresponding id number...", ConsoleColors.BLUE);

        var input = scanner.nextLine();

        var selectedPlayer = playerRepository.findPlayerById(Long.valueOf(input));

        return selectedPlayer;
    }
    public void showExistingPlayers(){
        List<Player> existingPlayers = playerRepository.findAll();
            for (int i = 0; i < existingPlayers.size(); i++) {
            utils.pause(1000);
            System.out.println(existingPlayers.get(i));
        }
    }



    public Player createNewPlayer (){
        String playerName = null;
        String playerPassword = null;


        while (playerName == null) {
            System.out.println("Please introduce your name: ");
            var tentativeName = scanner.nextLine();

            try {
                if (!inputValidations.validateLettersAndNumbersOnly(tentativeName)) {
                    System.out.println("Wrong input. Please use letters and numbers only, without spaces nor special characters'.\n");

                }
                if (inputValidations.validateExistingName(tentativeName)){
                    System.out.println("This player name already exists, please choose another one");
                } else playerName = tentativeName;
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong input. Please use letters and numbers only, without spaces nor special characters'.\n");
                e.getMessage();
            }
        }
        System.out.println("Selected name = " + playerName);

        while (playerPassword == null){
            System.out.println("\nPlease introduce your password: ");
            var input = scanner.nextLine();
            var tentativePassword1 = input;

            System.out.println("Please repeat your password: ");
            var tentativePassword2 = scanner.nextLine();

            if (!tentativePassword1.equals(tentativePassword2)){
                System.out.println("The passwords don't match, please try again.");
            } else {
                playerPassword = tentativePassword1;
            }
        }

        var newPlayer = new Player(playerName, playerPassword);

        return  playerRepository.save(newPlayer);
    }

}
