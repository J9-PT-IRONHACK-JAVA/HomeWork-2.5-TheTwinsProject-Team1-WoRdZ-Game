package com.ironhack.thetwinsprojectwords.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private int score;
    private int maxScore;
    private int accumulatedScore;

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        this.score = 0;
        this.maxScore = 0;
        this.accumulatedScore = 0;
    }

    @Override
    public String toString() {
        return "******** ***** *** ** * *\n" +
                "   Player: \n" +
                "       id = " + id + "\n" +
                "       name = " + name + "\n" +
                "       password = " + "\uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D" + "\n" +
                "       score=" + score + "\n" +
                "       maxScore=" + maxScore + "\n" +
                "       accumulatedScore=" + accumulatedScore + "\n" +
                "******** ***** *** ** * *\n";
    }
}
