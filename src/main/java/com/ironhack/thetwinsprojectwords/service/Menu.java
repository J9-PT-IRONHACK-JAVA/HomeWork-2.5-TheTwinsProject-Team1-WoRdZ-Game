package com.ironhack.thetwinsprojectwords.service;

import com.ironhack.thetwinsprojectwords.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class Menu {
    private final Scanner scanner;
    private final PlayerService playerService;

    public void intro (){
        welcomeBanner();
        playerSelection();
    }

    public void generalMenu(){
        System.out.println("""
                >>> >> > >
                How would you like to proceed?...
                
                [0] Start a new WoRdZ game
                [1] Change player                
                """);

        var input = scanner.nextLine();

        try {
            switch (input){
                case "0" -> {

                }
                case "1" -> {
                    playerSelection();
                }
            }

//            if (!(input == "0" || input == "1")){
//                System.out.println("Wrong input. Please introduce either '0' or '1'.\n");
//            } else if (input == "0") {
//
//
//            } else if (input == "1") {
//                playerSelection();
//            }

        } catch (IllegalArgumentException e){
            System.out.println("Wrong input. Please introduce either '0' or '1'.\n");
            e.getMessage();
        }
    }

    public void welcomeBanner(){

        System.out.println("""
                
                ******** ***** *** ** * *
                            Welcome,
                        ... let's play...
                            
                            WoRdZ!!!
                ******** ***** *** ** * *
                """);
    }

    public void playerSelection(){
        Player chosenPlayer = null;

        System.out.println("""
                >>> >> > >
                How would you like to proceed?...
                
                [0] Use an existing player
                [1] Create a New Player
                
                """);
        var input = scanner.nextLine();

        try {
            switch (input){
                case "0" -> {
                    var playerFromList = playerService.selectExistingPlayer();
                    chosenPlayer = playerFromList;
                }
                case "1" -> {
                    var newPlayer = playerService.createNewPlayer();
                    chosenPlayer = newPlayer;
                }
            }

//            if (!(input == "0" || input == "1")){
//                System.out.println("Wrong input. Please introduce either '0' or '1'.\n");
//            } else if (input == "0") {
//                var playerFromList = playerService.selectExistingPlayer();
//                chosenPlayer = playerFromList;
//
//            } else if (input == "1") {
//                var newPlayer = playerService.createNewPlayer();
//                chosenPlayer = newPlayer;
//            }

        } catch (IllegalArgumentException e){
            System.out.println("Wrong input. Please introduce either '0' or '1'.\n");
            e.getMessage();
        }

        System.out.println("""
                ***** *** ** * *
                The chosen player is...
                """);
        System.out.println(chosenPlayer);

    }




}
