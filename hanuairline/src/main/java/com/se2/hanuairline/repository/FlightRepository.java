package com.se2.hanuairline.repository;

import com.se2.hanuairline.model.Flight;
import com.se2.hanuairline.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Optional<Flight>> findByAircraft_id(Long aircraft_id);

    // Find all flight distinct by aircraft sorted by the latest departure time
    // has the arrival time less than the given time and the arrival airport id is the given airport
    List<Flight> find();
}
