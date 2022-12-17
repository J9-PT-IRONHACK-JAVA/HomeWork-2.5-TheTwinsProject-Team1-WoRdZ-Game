package com.ironhack.thetwinsprojectwords.service;

import com.ironhack.thetwinsprojectwords.model.Player;
import com.ironhack.thetwinsprojectwords.repository.PlayerRepository;
import com.ironhack.thetwinsprojectwords.uitls.ConsoleColors;
import com.ironhack.thetwinsprojectwords.uitls.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class Menu {
    private final Scanner scanner;
    private final Utils utils;
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    private final Game game;

    public static Player currentPlayer;
    public static int currentScore;

    public void intro (){
        welcomeBanner();
        currentPlayer = playerSelection();
    }

    public void generalMenu(){
        var endGame="";
        while (!endGame.equals("exit")) {
            System.out.println("""
                    .
                    .
                    ..
                    ...
                    How would you like to proceed?...
                                    
                    [1] Start a new WoRdZ game - ARCADE
                    [2] Start a new WoRdZ game - MANUAL
                    [3] Show HALL OF FAME
                    [4] Change player
                    [5] EXIT
                    """);

            var input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    currentScore = game.play("AUTO");
                }
                case "2" -> {
                    currentScore = game.play("MANUAL");
                }
                case "3" -> {
                    hallOfFame();
                }
                case "4" -> {
                    currentPlayer = playerSelection();
                }
                case "5" -> {
                    endGame = "exit";
                    System.exit(0);
                }
                default -> {
                    System.out.println("Wrong input. Please type a valid numbered option");
                }
            }
            currentPlayer.setScore(currentScore);
            playerRepository.save(currentPlayer);
            utils.clearScreen();
        }
    }

    public void welcomeBanner(){
        utils.clearScreen();

        utils.printLogo();

        System.out.println("""

                ******** ***** *** ** * ** *** ***** ********
                                    Welcome,
                                ... let's play...
                """);

        utils.printWithColor("""
                                    WoRdZ!!!
                """, ConsoleColors.BLUE_BOLD);

        System.out.println("""
                ******** ***** *** ** * ** *** ***** ********
                """);
    }

    public Player playerSelection(){
        Player chosenPlayer = null;

        while (chosenPlayer == null){
            System.out.println("""
                .
                .
                ..
                ...
                How would you like to proceed?...
                
                [0] Use an existing player
                [1] Create a New Player
                
                """);
            var input = scanner.nextLine();

            try {
                switch (input){
                    case "0" -> {
                        utils.clearScreen();
                        var playerFromList = playerService.selectExistingPlayer();
                        chosenPlayer = playerFromList;
                    }
                    case "1" -> {
                        utils.clearScreen();
                        var newPlayer = playerService.createNewPlayer();
                        chosenPlayer = newPlayer;
                    }
                }

            } catch (IllegalArgumentException e){
                System.out.println("Wrong input. Please introduce either '0' or '1'.\n");
                e.getMessage();
            }
        }

        System.out.println("""
                .
                .
                ..
                ...
                The chosen player is...
                """);

        System.out.println(chosenPlayer);

        return chosenPlayer;
    }


    public void hallOfFame(){
        utils.clearScreen();
        var allPlayers = playerRepository.findAll();
        var maxScoresList = new HashMap<Long, Integer>();

        for(Player player : allPlayers) {
            maxScoresList.put(player.getId(),player.getMaxScore());
        }

        LinkedHashMap<Long, Integer> rankedScores = new LinkedHashMap<>();
        List<Integer> list = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : maxScoresList.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        for (int num : list) {
            for (Map.Entry<Long, Integer> entry : maxScoresList.entrySet()) {
                if (entry.getValue().equals(num)) {
                    rankedScores.put(entry.getKey(), num);
                }
            }
        }
        utils.printWithColor("HALL OF FAME", ConsoleColors.CYAN_BOLD);
        utils.printWithColor(String.format("%-30s %-30s %-30s","Player Name","Max Score", "Accumulated Score"), ConsoleColors.CYAN_BOLD);
        utils.printWithColor("============================================================================================", ConsoleColors.CYAN_BOLD);
        for (Long id : rankedScores.keySet()) {
            var player = playerRepository.findById(id).get();
            utils.printWithColor(String.format("%-30s %-30s %-30s", player.getName(),player.getMaxScore(), player.getAccumulatedScore()), ConsoleColors.CYAN_BOLD);
        }
        utils.printWithColor("============================================================================================", ConsoleColors.CYAN_BOLD);

        utils.promptEnterKey();

    }
}
