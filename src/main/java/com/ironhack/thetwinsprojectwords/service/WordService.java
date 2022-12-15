package com.ironhack.thetwinsprojectwords.service;

import com.ironhack.thetwinsprojectwords.dto.WordDTO;
import com.ironhack.thetwinsprojectwords.proxy.WordsProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordsProxy wordsProxy;

    public List<WordDTO> getAllRelatedWords(String word){
        var allResults = wordsProxy.getAllRelatedWords(word);
        var count = allResults.size();
        var startRange = 0;
        var numOfWordsChosen = Math.min(6, allResults.size());
        if (count %2 == 0) startRange = count/2 - numOfWordsChosen/2;
        else startRange = (count+1)/2 - numOfWordsChosen/2;
        List<WordDTO> shortList = null;
        for (int i = startRange; i < numOfWordsChosen; i++) {
            shortList.add(allResults.get(i));
        } return shortList;
    }

    public List<String> getResults(String word){
        List<String> listOfWords = null;
        var relatedWords = getAllRelatedWords(word);
        for (WordDTO w : relatedWords){
            listOfWords.add(w.getWord());
        } return listOfWords;
    }

    public boolean compareAnswer(String inputWord){
        var referenceWord = generateReferenceWord();
        var listOfOptions = getResults(referenceWord);
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

        //TODO Crear un faker de palabras

        return "cow";
    }


}
