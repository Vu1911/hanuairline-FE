package com.se2.hanuairline.repository.airport;

import com.se2.hanuairline.model.airport.Gate;
import com.se2.hanuairline.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateRepository extends JpaRepository<Gate, Long> {
    List<Gate> findByAirport_Id(Long id);
}
