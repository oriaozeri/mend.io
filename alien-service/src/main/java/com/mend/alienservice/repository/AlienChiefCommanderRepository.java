package com.mend.alienservice.repository;

import com.mend.alienservice.model.AlienChiefCommander;
import com.mend.alienservice.model.AlienCommander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlienChiefCommanderRepository extends JpaRepository<AlienChiefCommander, Long> {
}
