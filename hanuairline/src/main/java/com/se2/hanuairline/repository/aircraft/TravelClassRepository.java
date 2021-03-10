package com.se2.hanuairline.repository.aircraft;

import com.se2.hanuairline.model.aircraft.TravelClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelClassRepository extends JpaRepository<TravelClass, Long> {
}
