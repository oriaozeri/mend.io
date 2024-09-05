package com.mend.alienservice.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commander")

public class AlienCommander {
    public static final int MAX_WARRIORS = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long commanderId;

    @Enumerated(EnumType.STRING)
    private Vehicle vehicle;

    @Getter
    @ElementCollection
    private List<Long> alienWarriorIds = new ArrayList<>();

    public enum Vehicle {
        BIRD_SCOOTER,
        MERKAVA_TANK
    }

}
