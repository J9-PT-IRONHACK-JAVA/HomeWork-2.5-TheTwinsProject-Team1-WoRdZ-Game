package com.ironhack.thetwinsprojectwords.uitls;

import com.ironhack.thetwinsprojectwords.TheTwinsProjectWordsApplication;
import com.ironhack.thetwinsprojectwords.model.Player;
import com.ironhack.thetwinsprojectwords.repository.PlayerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InputValidationsTest {

    @MockBean
    TheTwinsProjectWordsApplication theTwinsProjectWordsApplication;

    @Autowired
    private InputValidations inputValidations;
    @Autowired
    private PlayerRepository playerRepository;


    @BeforeEach
    void setUp() {
        var testPayer = new Player("Pedro", "123456");
        playerRepository.save(testPayer);
    }

    @AfterEach
    void tearDown() {
//        playerRepository.deleteAll();
    }

    @Test
    void validateLettersOnly() {
        var isValidated = inputValidations.validateLettersAndNumbersOnly("juan");

        assertTrue(isValidated);
    }

    @Test
    void validateLettersAndNumbers() {
        var isValidated = inputValidations.validateLettersAndNumbersOnly("juan352");

        assertTrue(isValidated);
    }

    @Test
    void invalidateWithSpecialCharacters() {
        var isValidated = inputValidations.validateLettersAndNumbersOnly("juan:)");

        assertFalse(isValidated);
    }

    @Test
    void invalidateWithSpace() {
        var isValidated = inputValidations.validateLettersAndNumbersOnly("juan 123");

        assertFalse(isValidated);
    }

    @Test
    void validateNonExistingName() {
        var testPlayer = new Player("pepe555", "321321");
        playerRepository.save(testPlayer);
        assertTrue(inputValidations.validateExistingName("pepe555"));
    }
}