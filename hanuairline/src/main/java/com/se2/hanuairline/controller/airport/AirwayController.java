package com.se2.hanuairline.controller.airport;

import com.se2.hanuairline.model.airport.Airport;
import com.se2.hanuairline.model.airport.Airway;
import com.se2.hanuairline.payload.airport.AirwayPayload;
import com.se2.hanuairline.repository.airport.AirwayRepository;
import com.se2.hanuairline.repository.airport.AirportRepository;
import com.se2.hanuairline.service.airport.AirwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/airway")
public class AirwayController {

    @Autowired
    private AirwayRepository airwayRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirwayService airwayService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAirway(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id,desc") String[] sort) {
        try {
            Page<Airway> airways = airwayService.findAll(page, size, sort);

            return new ResponseEntity<>(airways, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getAirwayByArrivalAndDepartureName(@RequestParam(required = true) String arrivalAirport,
                                                                @RequestParam(required = true) String departureAirport) {
        Airway airwayData = airwayService.findByArrivalAirportAndDepartureAirport(arrivalAirport, departureAirport);

        return new ResponseEntity<>(airwayData, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAirway(@Valid @RequestBody AirwayPayload request) {
        try {
            Optional<Airport> arrival_airportData = airportRepository.findById(request.getArrival_airport_id());
            Optional<Airport> departure_airportData = airportRepository.findById(request.getDeparture_airport_id());

            if(!arrival_airportData.isPresent()){
                return new ResponseEntity<>("AirwayController: arrival airport not found", HttpStatus.NOT_FOUND);
            }

            if(!departure_airportData.isPresent()){
                return new ResponseEntity<>("AirwayController: departure airport not found", HttpStatus.NOT_FOUND);
            }

            Airway airway = new Airway();
            Airport arrival_airport = arrival_airportData.get();
            Airport departure_airport = departure_airportData.get();

            airway.setArrivalAirport(arrival_airport);
            airway.setDepartureAirport(departure_airport);
            airway.setDistanceKm(request.getDistance_km());

            Airway _airway = airwayRepository.save(airway);

            return new ResponseEntity<>(_airway, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateAirway(@PathVariable("id") long id, @RequestBody AirwayPayload request) {
        try {
            Optional<Airway> airwayData = airwayRepository.findById(request.getId());
            Optional<Airport> arrival_airportData = airportRepository.findById(request.getArrival_airport_id());
            Optional<Airport> departure_airportData = airportRepository.findById(request.getDeparture_airport_id());

            if(!airwayData.isPresent()){
                return new ResponseEntity<>("AirwayController: airway not found", HttpStatus.NOT_FOUND);
            }

            if(!arrival_airportData.isPresent()){
                return new ResponseEntity<>("AirwayController: arrival airport not found", HttpStatus.NOT_FOUND);
            }

            if(!departure_airportData.isPresent()){
                return new ResponseEntity<>("AirwayController: departure airport not found", HttpStatus.NOT_FOUND);
            }

            Airway airway = airwayData.get();
            Airport arrival_airport = arrival_airportData.get();
            Airport departure_airport = departure_airportData.get();

            airway.setArrivalAirport(arrival_airport);
            airway.setDepartureAirport(departure_airport);
            airway.setDistanceKm(request.getDistance_km());

            Airway _airway = airwayRepository.save(airway);

            return new ResponseEntity<>(_airway, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Becareful !!!!!
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAirway(@PathVariable("id") long id) {
        try {
            airwayRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
