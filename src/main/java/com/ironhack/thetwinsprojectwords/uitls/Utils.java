package com.ironhack.thetwinsprojectwords.uitls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class Utils {

    private final Scanner scanner;

    public void promptEnterKey() {
        printWithColor("\nPress ENTER to continue...", ConsoleColors.BLUE);
        scanner.nextLine();
    }

    public void clearScreen(){
        for (int i = 0; i < 20; i++) {
            System.out.println("\n");
        }
    }

    public void pause(int milis){
        milis = Math.min(milis,5000);
        try{
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
    }

    public void printWithColor(String text, String color){
        System.out.println(color + text + ConsoleColors.RESET);
    }

    public void printInvalidCommand(){
        printWithColor("Please type a valid command. For more information, type 'help'", ConsoleColors.RED);
        promptEnterKey();;
    }




}
