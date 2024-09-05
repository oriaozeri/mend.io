package com.mend.alienservice.service;

import com.mend.alienservice.dto.AlienDTO;
import com.mend.alienservice.mapper.MapAllAliensToAlienDTO;
import com.mend.alienservice.repository.AlienChiefCommanderRepository;
import com.mend.alienservice.repository.AlienCommanderRepository;
import com.mend.alienservice.model.AlienChiefCommander;
import com.mend.alienservice.model.AlienCommander;
import com.mend.alienservice.model.AlienWarrior;
import com.mend.alienservice.repository.AlienWarriorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlienService implements IAlienService {
    private final AlienWarriorRepository alienWarriorRepository;
    private final AlienCommanderRepository alienCommanderRepository;
    private final AlienChiefCommanderRepository alienChiefCommanderRepository;
    private final MapAllAliensToAlienDTO mapper;

    @Override
    public void addAlienWarrior(AlienWarrior warrior) {
        alienWarriorRepository.save(warrior);
    }

    @Override
    public void addAlienCommander(AlienCommander commander) {
        alienCommanderRepository.save(commander);
    }

    @Override
    public void addAlienChiefCommander(AlienChiefCommander chiefCommander) {
        alienChiefCommanderRepository.save(chiefCommander);
    }

    @Override
    public List<AlienDTO> getAllAliens() {
        List<AlienWarrior> warriors = alienWarriorRepository.findAll();
        List<AlienCommander> commanders = alienCommanderRepository.findAll();
        List<AlienChiefCommander> chiefCommanders = alienChiefCommanderRepository.findAll();

        List<AlienDTO> alienDTOs = new ArrayList<>();
        alienDTOs.addAll(mapper.mapWarriorsToDTOs(warriors, commanders));
        alienDTOs.addAll(mapper.mapCommandersToDTOs(commanders, chiefCommanders));
        alienDTOs.addAll(mapper.mapChiefCommandersToDTOs(chiefCommanders));

        return alienDTOs;
    }
}

