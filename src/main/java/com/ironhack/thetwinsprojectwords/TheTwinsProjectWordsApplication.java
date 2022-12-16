package com.ironhack.thetwinsprojectwords;

import com.ironhack.thetwinsprojectwords.proxy.WordsProxy;
import com.ironhack.thetwinsprojectwords.service.Game;
import com.ironhack.thetwinsprojectwords.service.Menu;
import com.ironhack.thetwinsprojectwords.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Scanner;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
public class TheTwinsProjectWordsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TheTwinsProjectWordsApplication.class, args);
    }

    private final WordService wordService;
    private final Menu menu;
    private final Scanner scanner;

    @Override
    public void run(String... args) throws Exception {
        menu.intro();
        menu.generalMenu();




//        System.out.println(wordService.generateReferenceWord());
//        System.out.println(wordService.convertResultsIntoStrings(
//                wordService.shortenResultsToCompare(wordService.getAllRelatedWords("cow"), 6)));


        scanner.close();
    }
}
