package com.ironhack.thetwinsprojectwords.uitls;

import com.ironhack.thetwinsprojectwords.model.Player;
import com.ironhack.thetwinsprojectwords.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("demo-data")
@RequiredArgsConstructor
@Log
public class DataLoader {

    private final PlayerRepository playerRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void leadData(){
        log.info("loading demo data");
        var player1 = new Player("iAmPlayer1", "123456");
        playerRepository.save(player1);
    }

}
