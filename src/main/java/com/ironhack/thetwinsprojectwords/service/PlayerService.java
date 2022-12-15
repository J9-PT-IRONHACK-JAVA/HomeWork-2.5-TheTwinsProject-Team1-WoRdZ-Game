package com.ironhack.thetwinsprojectwords.service;

import com.ironhack.thetwinsprojectwords.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

}
