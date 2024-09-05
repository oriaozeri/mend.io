package com.mend.alienservice.mapper;

import com.mend.alienservice.dto.AlienDTO;
import com.mend.alienservice.model.AlienChiefCommander;
import com.mend.alienservice.model.AlienCommander;
import com.mend.alienservice.model.AlienWarrior;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapAllAliensToAlienDTO {

    public List<AlienDTO> mapWarriorsToDTOs(List<AlienWarrior> warriors, List<AlienCommander> commanders) {
        return warriors.stream().map(warrior -> {
            Long commanderId = warrior.getCommanderId();
            AlienCommander commander = commanders.stream()
                    .filter(cmdr -> cmdr.getId().equals(commanderId))
                    .findFirst()
                    .orElse(null);
            return new AlienDTO(
                    warrior.getId(),
                    warrior.getName(),
                    null,
                    warrior.getWeapon().toString(),
                    commander != null ? commander.getId() : null,
                    commander != null ? commander.getName() : null
            );
        }).collect(Collectors.toList());
    }

    public List<AlienDTO> mapCommandersToDTOs(List<AlienCommander> commanders, List<AlienChiefCommander> chiefCommanders) {
        return commanders.stream().map(commander -> {
            Long chiefCommanderId = commander.getCommanderId();
            AlienChiefCommander chiefCommander = chiefCommanders.stream()
                    .filter(chiefCmdr -> chiefCmdr.getId().equals(chiefCommanderId))
                    .findFirst()
                    .orElse(null);
            return new AlienDTO(
                    commander.getId(),
                    commander.getName(),
                    commander.getVehicle().toString(),
                    null,
                    chiefCommander != null ? chiefCommander.getId() : null,
                    chiefCommander != null ? chiefCommander.getName() : null
            );
        }).collect(Collectors.toList());
    }
    public List<AlienDTO> mapChiefCommandersToDTOs(List<AlienChiefCommander> chiefCommanders) {
        return chiefCommanders.stream().map(chiefCommander ->
                new AlienDTO(
                        chiefCommander.getId(),
                        chiefCommander.getName(),
                        chiefCommander.getVehicle().toString(),
                        "",
                        null,
                        null)
                        ).collect(Collectors.toList());
    }
}