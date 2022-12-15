package com.ironhack.thetwinsprojectwords.proxy;

import com.ironhack.thetwinsprojectwords.dto.WordDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name = "words", url = "https://api.datamuse.com")
public interface WordsProxy {

//    @GetMapping("{word}")
//    List<WordDTO> getAllRelatedWords(@PathVariable String word);

    @GetMapping("/words")
    List<WordDTO> getAllRelatedWords(@RequestParam(name = "rel_trg") String rel_trg);

}

