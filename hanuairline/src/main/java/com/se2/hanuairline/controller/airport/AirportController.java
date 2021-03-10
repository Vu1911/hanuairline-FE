package com.se2.hanuairline.controller.airport;

import com.se2.hanuairline.model.airport.Airport;
import com.se2.hanuairline.repository.airport.AirportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    private static final Logger logger = LoggerFactory.getLogger(com.se2.hanuairline.controller.user.UserController.class);

    @GetMapping("/getAll")
    public List<Airport> getAllAirport(@RequestParam(required = false) String tittle) {
        List<Airport> airports = airportRepository.findAll();

        return airports;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getAirportById(@PathVariable("id") long id) {
        Optional<Airport> airport = airportRepository.findById(id);

        if (airport.isPresent()) {
            return new ResponseEntity<>(airport.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAirport(@RequestBody Airport airport) {
        try {
            Airport clone = (Airport) airport.clone();
            Airport _airport = airportRepository
                    .save(clone);
            return new ResponseEntity<>(_airport, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateAirport(@PathVariable("id") long id, @RequestBody Airport airport) {
        Optional<Airport> airportData = airportRepository.findById(id);

        if (airportData.isPresent()) {
            try {
                Airport _airport = (Airport) airport.clone();

                return new ResponseEntity<>(airportRepository.save(_airport), HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAirport(@PathVariable("id") long id) {
        try {
            airportRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}