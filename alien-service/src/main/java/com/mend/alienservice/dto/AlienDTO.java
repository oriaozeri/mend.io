package com.mend.alienservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlienDTO {
    private Long id;

    @NotNull
    private String name;

    private String vehicle;

    private String weapon;

    private Long commanderId;

    private String commanderName;

    public AlienDTO(Long id, String name, String weapon, Long commanderId, String commanderName, String vehicle){
        this.id = id;
        this.name = name;
        this.vehicle = vehicle;
        this.weapon = weapon;
        this.commanderId = commanderId;
        this.commanderName = commanderName;

    }

}