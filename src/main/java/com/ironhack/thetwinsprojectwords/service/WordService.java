package com.ironhack.thetwinsprojectwords.service;

import com.ironhack.thetwinsprojectwords.dto.WordDTO;
import com.ironhack.thetwinsprojectwords.proxy.WordsProxy;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordsProxy wordsProxy;

    public List<WordDTO> getAllRelatedWords(String word){
        return wordsProxy.getAPIWords(word);
    }

    public List<WordDTO> shortenResultsToCompare(List<WordDTO> listOfAPIResults, Integer maxWords) {
        var count = listOfAPIResults.size();
        var startRange = 0;
        float numOfWordsChosen = Math.min(maxWords, listOfAPIResults.size());
        var segment = Math.round(numOfWordsChosen/2);

        if (count % 2 == 0) startRange = count / 2 - segment + 1;
        else startRange = (count + 1) / 2 - segment + 1;

        List<WordDTO> shortListResults = new ArrayList<>();
        for (int i = startRange; i < (startRange+numOfWordsChosen); i++) {
            shortListResults.add(listOfAPIResults.get(i));
        }
        return shortListResults;
    }

    public List<String> convertResultsIntoStrings (List<WordDTO> listOfWordsDTO){
        List<String> listOfWords = new ArrayList<>();
        for (WordDTO w : listOfWordsDTO){
            listOfWords.add(w.getWord());
        } return listOfWords;
    }

    public boolean compareAnswer(String inputWord, String referenceWord){
        var maxWords = 12;
        var listOfOptions = convertResultsIntoStrings(shortenResultsToCompare(getAllRelatedWords(referenceWord), maxWords));
        boolean match = false;
        for (String wordOption : listOfOptions){
            if (wordOption.equalsIgnoreCase(inputWord)) {
                match = true;
                break;
            }
        }
        return match;
    }

    public String generateReferenceWord(){
        Faker faker = new Faker();
        String referenceWord = faker.animal().name();
        return referenceWord;
    }


}
