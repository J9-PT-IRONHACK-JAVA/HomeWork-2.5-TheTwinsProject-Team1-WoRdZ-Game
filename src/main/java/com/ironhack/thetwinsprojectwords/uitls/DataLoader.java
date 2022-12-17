package com.ironhack.thetwinsprojectwords.uitls;

import com.ironhack.thetwinsprojectwords.model.Player;
import com.ironhack.thetwinsprojectwords.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Configuration
//@Profile("demo-data")
@RequiredArgsConstructor
@Log
public class DataLoader {

    private final PlayerRepository playerRepository;

    @Bean
    public void loadData(){
        log.info("loading demo data");

        var player1 = new Player("Berto", "medaascotufamilia");
        player1.setScore(3);
        player1.setMaxScore(5);
        player1.setAccumulatedScore(35);
        playerRepository.save(player1);

        var player2 = new Player("andreu", "samante");
        player2.setScore(0);
        player2.setMaxScore(2);
        player2.setAccumulatedScore(8);
        playerRepository.save(player2);

        var player3 = new Player("PioB", "las_calles-siniestras_");
        player3.setScore(4);
        player3.setMaxScore(4);
        player3.setAccumulatedScore(84);
        playerRepository.save(player3);

        var player4 = new Player("ruben", "ojosDePerroAzul");
        player4.setScore(4);
        player4.setMaxScore(4);
        player4.setAccumulatedScore(62);
        playerRepository.save(player4);

        var player5 = new Player("jHuenun", "lacaidadelacasakisinger");
        player5.setScore(2);
        player5.setMaxScore(3);
        player5.setAccumulatedScore(57);
        playerRepository.save(player5);

    }



}
