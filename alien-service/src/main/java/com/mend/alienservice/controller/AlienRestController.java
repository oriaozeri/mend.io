package com.mend.alienservice.controller;

import com.mend.alienservice.dto.AlienDTO;
import com.mend.alienservice.model.AlienChiefCommander;
import com.mend.alienservice.model.AlienCommander;
import com.mend.alienservice.model.AlienWarrior;
import com.mend.alienservice.repository.AlienChiefCommanderRepository;
import com.mend.alienservice.repository.AlienCommanderRepository;
import com.mend.alienservice.service.AlienService;
import com.mend.alienservice.webSocket.AlienWebSocketHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aliens")

public class AlienRestController {

    private final AlienService alienService;
    private final AlienCommanderRepository alienCommanderRepository;
    private final AlienChiefCommanderRepository alienChiefCommanderRepository;
    private final AlienWebSocketHandler alienWebSocketHandler;

    public AlienRestController(AlienService alienService, AlienCommanderRepository alienCommanderRepository, AlienChiefCommanderRepository alienChiefCommanderRepository, AlienWebSocketHandler alienWebSocketHandler) {
        this.alienService = alienService;
        this.alienCommanderRepository = alienCommanderRepository;
        this.alienChiefCommanderRepository = alienChiefCommanderRepository;
        this.alienWebSocketHandler = alienWebSocketHandler;
    }

    @PostMapping("/addWarrior")
    ResponseEntity<AlienWarrior> addWarrior(@RequestBody AlienWarrior alienWarrior) throws IOException {
        Optional<AlienCommander> optionalCommander = alienCommanderRepository.findById(alienWarrior.getCommanderId());

        if (optionalCommander.isPresent()) {
            AlienCommander commander = optionalCommander.get();
            if (commander.getAlienWarriorIds().size() < AlienCommander.MAX_WARRIORS) {
                alienService.addAlienWarrior(alienWarrior);

                commander.getAlienWarriorIds().add(alienWarrior.getId());
                alienCommanderRepository.save(commander);
                alienWebSocketHandler.broadcast("Warrior added");

                return ResponseEntity.status(HttpStatus.CREATED).body(alienWarrior);
            }
        }
        AlienWarrior errorWarrior = new AlienWarrior();
        errorWarrior.setName("Commander not found or has reached the maximum number of warriors.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorWarrior);
    }

    @PostMapping("/addAlienCommander")
    public ResponseEntity<AlienCommander> addAlienCommander(@RequestBody AlienCommander commander) throws IOException {
        Optional<AlienChiefCommander> optionalChiefCommander = alienChiefCommanderRepository.findById(commander.getCommanderId());
        if (optionalChiefCommander.isPresent()) {
            AlienChiefCommander chiefCommander = optionalChiefCommander.get();
            if (chiefCommander.getAlienCommanderIds().size() < AlienChiefCommander.MAX_COMMANDERS) {
                alienService.addAlienCommander(commander);

                chiefCommander.getAlienCommanderIds().add(commander.getId());
                alienChiefCommanderRepository.save(chiefCommander);
                alienWebSocketHandler.broadcast("Commander added");

                return ResponseEntity.status(HttpStatus.CREATED).body(commander);
            }
        }
        AlienCommander errorCommander = new AlienCommander();
        errorCommander.setName("Chief Commander not found or has reached the maximum number of commanders.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorCommander);
    }

    @PostMapping("/addAlienChiefCommander")
    public ResponseEntity<AlienChiefCommander> addAlienChiefCommander(@RequestBody AlienChiefCommander chiefCommander) throws IOException {
        alienService.addAlienChiefCommander(chiefCommander);
        alienWebSocketHandler.broadcast("ChiefCommander added");

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<AlienDTO>> getAllAliens() {
        return ResponseEntity.status(HttpStatus.OK).body(alienService.getAllAliens());
    }
}