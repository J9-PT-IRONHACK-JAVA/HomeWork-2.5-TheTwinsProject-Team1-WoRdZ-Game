package com.ironhack.thetwinsprojectwords.controller;

//import com.ironhack.thetwinsprojectwords.dto.Results;
import com.ironhack.thetwinsprojectwords.dto.WordDTO;
import com.ironhack.thetwinsprojectwords.proxy.WordsProxy;
import com.ironhack.thetwinsprojectwords.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/words")
public class WordController {

    private final WordService wordService;
    private final WordsProxy wordsProxy;

    @GetMapping("/{word}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<WordDTO> getWordsFromAPI(@PathVariable String word){
        return wordsProxy.getAPIWords(word);
    }


}
