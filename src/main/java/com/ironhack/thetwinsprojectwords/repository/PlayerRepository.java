package com.ironhack.thetwinsprojectwords.repository;

import com.ironhack.thetwinsprojectwords.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {


}
