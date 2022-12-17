package com.ironhack.thetwinsprojectwords;

import com.ironhack.thetwinsprojectwords.service.Menu;
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

    private final Menu menu;

    @Override
    public void run(String... args) {
        menu.intro();
        menu.generalMenu();
    }
}
