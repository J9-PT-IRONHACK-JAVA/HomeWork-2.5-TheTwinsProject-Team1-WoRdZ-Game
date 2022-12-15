package com.ironhack.thetwinsprojectwords;

import com.ironhack.thetwinsprojectwords.proxy.WordsProxy;
import com.ironhack.thetwinsprojectwords.service.Menu;
import com.ironhack.thetwinsprojectwords.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
public class TheTwinsProjectWordsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TheTwinsProjectWordsApplication.class, args);
    }

    private final WordService wordService;
    private final WordsProxy wordsProxy;
    private final Menu menu;

    @Override
    public void run(String... args) throws Exception {
        //System.out.println(wordService.getAllRelatedWords("cow"));
        //System.out.println(wordsProxy.getAllRelatedWords("cow"));

        //menu.run();
    }
}
