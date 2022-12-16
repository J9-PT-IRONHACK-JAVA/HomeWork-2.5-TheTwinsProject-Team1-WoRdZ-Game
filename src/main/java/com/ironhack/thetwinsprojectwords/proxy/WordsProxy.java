package com.ironhack.thetwinsprojectwords.proxy;

import com.ironhack.thetwinsprojectwords.dto.WordDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name = "relatedWords", url = "https://api.datamuse.com")
public interface WordsProxy {

    @GetMapping("/words")
    List<WordDTO> getAPIWords(@RequestParam(name = "rel_trg") String rel_trg);

}

