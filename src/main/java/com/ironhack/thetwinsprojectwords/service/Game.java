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

    public int play(String gameMode){

        utils.clearScreen();
        utils.printWithColor("\nNEW GAME", ConsoleColors.BLUE_BOLD);
        var player = Menu.currentPlayer;

        var round = 1;
        var totalRounds = 5;
        var gameScore = 0;

        while (round <= totalRounds) {

            var welcome = String.format("""
            ********************************************
            Round %s/%s for Player: %s""", round, totalRounds, player.getName());
            utils.printWithColor("\n" + welcome + "\n", ConsoleColors.BLUE);

            var referenceWord = "";
            if (gameMode.equals("AUTO")) {
                referenceWord = wordService.generateReferenceWord();
            } else if (gameMode.equals("MANUAL")) {
                System.out.println("Please type your Reference WORD:");
                referenceWord = scanner.next().trim().toLowerCase();
                System.out.println("\nOK, now try to guess a related word\n");
            }
            utils.printWithColor("WORD: " + referenceWord, ConsoleColors.WHITE_BOLD_BRIGHT);
            var over = false;
            var leftAttempts = 3;
            var inputWord = "";
            while (!over) {
                inputWord = scanner.next().trim();
                if (wordService.compareAnswer(inputWord, referenceWord)) {
                    utils.printWithColor("BINGO! You got a match!", ConsoleColors.GREEN);
                    gameScore = gameScore + 1;
                    over = true;
                } else if(inputWord.equalsIgnoreCase("--BACK")){
                    break;
                } else {
                    leftAttempts = leftAttempts - 1;
                    utils.printWithColor(String.format("Wrong! You have %s more attempts\n", leftAttempts),ConsoleColors.YELLOW);
                    if (leftAttempts == 0) over = true;
                }
            }
            if (inputWord.equalsIgnoreCase("--BACK")) break;
            round = round + 1;
            utils.pause(1000);
            if (round <= totalRounds) System.out.println("\nPrepare for next word...");
            else utils.printWithColor("\nGAME OVER!", ConsoleColors.BLUE);
            utils.pause(2000);
        }

        utils.printWithColor("\nYour total score is: " + gameScore, ConsoleColors.BLUE_BOLD_BRIGHT);

        utils.promptEnterKey();
        return gameScore;
    }

}
