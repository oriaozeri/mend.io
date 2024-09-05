package com.mend.alienservice.service;

import com.mend.alienservice.dto.AlienDTO;
import com.mend.alienservice.model.AlienChiefCommander;
import com.mend.alienservice.model.AlienCommander;
import com.mend.alienservice.model.AlienWarrior;

import java.util.List;

public interface IAlienService {

    void addAlienWarrior(AlienWarrior warrior);

    void addAlienCommander(AlienCommander commander);

    void addAlienChiefCommander(AlienChiefCommander chiefCommander);

    List<AlienDTO> getAllAliens();
}
