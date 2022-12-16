package com.ironhack.thetwinsprojectwords.service;

import com.ironhack.thetwinsprojectwords.TheTwinsProjectWordsApplication;
import com.ironhack.thetwinsprojectwords.model.Player;
import com.ironhack.thetwinsprojectwords.repository.PlayerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayerServiceTest {

    @MockBean
    TheTwinsProjectWordsApplication theTwinsProjectWordsApplication;

    @Autowired
    private PlayerRepository playerRepository;


    @BeforeEach
    void setUp() {
        var testPayer = new Player("Pedro", "123456");
        playerRepository.save(testPayer);
    }

    @AfterEach
    void tearDown() {
        playerRepository.deleteAll();
    }

    @Test
    void createNewPlayer() {
        var count1 = playerRepository.count();

        var testPayer2 = new Player("Juan", "987654");
        playerRepository.save(testPayer2);
        var count2 = playerRepository.count();

        assertEquals(1, count2 - count1);
    }
}