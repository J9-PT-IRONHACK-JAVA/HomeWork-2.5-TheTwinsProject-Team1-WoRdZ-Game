package com.ironhack.thetwinsprojectwords.service;

import com.ironhack.thetwinsprojectwords.uitls.ConsoleColors;
import com.ironhack.thetwinsprojectwords.uitls.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class Game {

    private final WordService wordService;
    private final Scanner scanner;
    private final Utils utils;

    public void play(){

        utils.clearScreen();
        utils.printWithColor("\nNEW GAME", ConsoleColors.BLUE_BOLD);
        var player = "Pau"; //Menu.currentPlayer;
        var round = 1;
        var totalRounds = 5;
        var gameScore = 0;

        while (round <= totalRounds) {

            var welcome = String.format("""
            ********************************************
            Round %s/10 for Player: %s""", round, player);
            utils.printWithColor("\n" + welcome + "\n", ConsoleColors.BLUE);

            var referenceWord = wordService.generateReferenceWord();
            utils.printWithColor("WORD: " + referenceWord, ConsoleColors.WHITE_BOLD_BRIGHT);

            var over = false;
            var leftAttempts = 3;
            while (!over) {
                var inputWord = scanner.next().trim();
                if (wordService.compareAnswer(inputWord, referenceWord)) {
                    System.out.println("BINGO! You got a match!");
                    gameScore = gameScore + 1;
                    over = true;
                } else {
                    leftAttempts = leftAttempts - 1;
                    utils.printWithColor(String.format("Wrong! You have %s more attempts\n", leftAttempts),ConsoleColors.YELLOW);
                    if (leftAttempts == 0) over = true;
                }
            }
            round = round + 1;
            utils.pause(1000);
            if (round <= totalRounds) System.out.println("\nPrepare for next word...");
            else utils.printWithColor("\nGAME OVER!", ConsoleColors.BLUE);
            utils.pause(2000);
        }

        utils.printWithColor("\nYour total score is: " + gameScore, ConsoleColors.BLUE_BOLD_BRIGHT);

        //Menu.currentPlayer.setScore(gameScore);
        //TODO Luego en el menú cuando acaba el juego guardar el player en el repo, para que quede con la Score.

        utils.promptEnterKey();
    }

}
