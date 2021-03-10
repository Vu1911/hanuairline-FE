package com.se2.hanuairline.controller.airport;

import com.se2.hanuairline.model.airport.Airport;
import com.se2.hanuairline.model.airport.Gate;
import com.se2.hanuairline.payload.airport.GatePayload;
import com.se2.hanuairline.repository.airport.GateRepository;
import com.se2.hanuairline.repository.airport.AirportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gate")
public class GateController {

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private AirportRepository airportRepository;

    private static final Logger logger = LoggerFactory.getLogger(com.se2.hanuairline.controller.user.UserController.class);

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllGate(@RequestParam(required = false) String tittle) {
        try {
            List<Gate> gates = gateRepository.findAll();

            if(!(gates.size() > 0)){
                return new ResponseEntity<>("GateController: no gate", HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(gates, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getGateById(@PathVariable("id") long id) {
        try {
            Optional<Gate> gateData = gateRepository.findById(id);

            if(!gateData.isPresent()){
                return new ResponseEntity<>("GateController: gate not found", HttpStatus.NOT_FOUND);
            }

            Gate gate = gateData.get();

            return new ResponseEntity<>(gate, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGate(@RequestBody GatePayload request) {
        try {
            Optional<Airport> airportData = airportRepository.findById(request.getAirport_id());

            if(!airportData.isPresent()){
                return new ResponseEntity<>("GateController: airport not found", HttpStatus.NOT_FOUND);
            }

            Gate gate = new Gate();
            Airport airport = airportData.get();

            gate.setName(request.getName());
            gate.setAirport(airport);

            Gate _gate = gateRepository.save(gate);

            return new ResponseEntity<>(_gate, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateGate(@PathVariable("id") long id, @RequestBody GatePayload request) {
        try {
            Optional<Gate> gateData = gateRepository.findById(request.getId());
            Optional<Airport> airportData = airportRepository.findById(request.getAirport_id());

            if(!gateData.isPresent()){
                return new ResponseEntity<>("GateController: gate not found", HttpStatus.NOT_FOUND);
            }

            if(!airportData.isPresent()){
                return new ResponseEntity<>("GateController: airport not found", HttpStatus.NOT_FOUND);
            }

            Gate gate = gateData.get();
            Airport airport = airportData.get();

            gate.setName(request.getName());
            gate.setAirport(airport);

            Gate _gate = gateRepository.save(gate);

            return new ResponseEntity<>(_gate, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Dont use this
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteGate(@PathVariable("id") long id) {
        try {
            gateRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
