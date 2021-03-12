package com.se2.hanuairline.service.airport;

import com.se2.hanuairline.model.airport.Gate;
import com.se2.hanuairline.repository.airport.GateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateService {

    @Autowired
    private GateRepository gateRepository;

    public List<Gate> findGateByAirport (Long airport_id){
        return gateRepository.findByAirport_Id(airport_id);
    }

}
