package com.mend.alienservice.repository;

import com.mend.alienservice.model.AlienWarrior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlienWarriorRepository extends JpaRepository<AlienWarrior, Long> {
}
