
package com.mend.alienservice.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chief_commander")
public class AlienChiefCommander {
    public static final int MAX_COMMANDERS = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Vehicle vehicle;

    @ElementCollection
    @CollectionTable(name = "chief_commander_commander_ids", joinColumns = @JoinColumn(name = "chief_commander_id"))
    @Column(name = "commander_id")
    private List<Long> alienCommanderIds = new ArrayList<>();

    public enum Vehicle {
        BIRD_SCOOTER,
        MERKAVA_TANK,
        EGGED_BUS
    }
}
