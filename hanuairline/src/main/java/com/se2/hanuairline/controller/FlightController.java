package com.se2.hanuairline.controller;

import com.se2.hanuairline.model.DiscountEvent;
import com.se2.hanuairline.model.Flight;
import com.se2.hanuairline.model.FlightStatus;
import com.se2.hanuairline.model.aircraft.Aircraft;
import com.se2.hanuairline.model.airport.Airway;
import com.se2.hanuairline.model.airport.Gate;
import com.se2.hanuairline.payload.FlightPayload;
import com.se2.hanuairline.repository.FlightRepository;
import com.se2.hanuairline.repository.airport.AirwayRepository;
import com.se2.hanuairline.repository.airport.GateRepository;
import com.se2.hanuairline.repository.aircraft.AircraftRepository;
import com.se2.hanuairline.repository.DiscountEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private DiscountEventRepository discountEventRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AirwayRepository airwayRepository;

    @Autowired
    private GateRepository gateRepository;

    private static final Logger logger = LoggerFactory.getLogger(com.se2.hanuairline.controller.user.UserController.class);

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllFlight(@RequestParam(required = false) String tittle) {
        try {
            List<Flight> flights = flightRepository.findAll();

            if(flights.isEmpty()){
                return new ResponseEntity<>("FlightController: No flight", HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(flights, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable("id") long id) {
        try {
            Optional<Flight> flightData = flightRepository.findById(id);

            if(!flightData.isPresent()){
                return new ResponseEntity<>("FlightController: flight not found", HttpStatus.NOT_FOUND);
            }

            Flight flight = flightData.get();

            return new ResponseEntity<>(flight, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFlight(@Valid @RequestBody FlightPayload request) {
        try {
            Optional<Aircraft> aircraftData = aircraftRepository.findById(request.getAircraft_id());
            Optional<Airway> airwayData = airwayRepository.findById(request.getAirway_id());
            Optional<Gate> arrival_gateData = gateRepository.findById(request.getArrival_gate_id());
            Optional<Gate> departure_gateData = gateRepository.findById(request.getDeparture_gate_id());

            Flight flight = new Flight();

            if (request.getDiscount_id() != null){
                Optional<DiscountEvent> discountEventData = discountEventRepository.findById(request.getDiscount_id());
                if(!discountEventData.isPresent()){
                    return new ResponseEntity<>("FlightController: discount event not found", HttpStatus.NOT_FOUND);
                }

                flight.setDiscount(discountEventData.get());
            } else {
                flight.setDiscount(null);
            }

            if(!aircraftData.isPresent()){
                return new ResponseEntity<>("FlightController: aircraft not found", HttpStatus.NOT_FOUND);
            }

            if(!airwayData.isPresent()){
                return new ResponseEntity<>("FlightController: airway not found", HttpStatus.NOT_FOUND);
            }

            if(!arrival_gateData.isPresent()){
                return new ResponseEntity<>("FlightController: arrival gate not found", HttpStatus.NOT_FOUND);
            }

            if(!departure_gateData.isPresent()){
                return new ResponseEntity<>("FlightController: departure gate not found", HttpStatus.NOT_FOUND);
            }

            if(!aircraftData.isPresent()){
                return new ResponseEntity<>("FlightController: aircraft not found", HttpStatus.NOT_FOUND);
            }


            flight.setAircraft(aircraftData.get());
            flight.setAirway(airwayData.get());
            flight.setArrivalGate(arrival_gateData.get());
            flight.setArrivalTime(request.getArrival_time());
            flight.setDepartureGate(departure_gateData.get());
            flight.setDepartureTime(request.getDeparture_time());
            flight.setStatus(FlightStatus.valueOf(request.getStatus()));

            Flight _flight = flightRepository.save(flight);

            return new ResponseEntity<>(_flight, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateflight(@PathVariable("id") long id, @RequestBody FlightPayload request) {
        try {
            Optional<Flight> flightData = flightRepository.findById(request.getId());
            Optional<Aircraft> aircraftData = aircraftRepository.findById(request.getAircraft_id());
            Optional<Airway> airwayData = airwayRepository.findById(request.getAirway_id());
            Optional<Gate> arrival_gateData = gateRepository.findById(request.getArrival_gate_id());
            Optional<Gate> departure_gateData = gateRepository.findById(request.getDeparture_gate_id());
            Optional<DiscountEvent> discountEventData = discountEventRepository.findById(request.getDiscount_id());

            if(!flightData.isPresent()){
                return new ResponseEntity<>("FlightController: flight not found", HttpStatus.NOT_FOUND);
            }

            if(!aircraftData.isPresent()){
                return new ResponseEntity<>("FlightController: aircraft not found", HttpStatus.NOT_FOUND);
            }

            if(!airwayData.isPresent()){
                return new ResponseEntity<>("FlightController: airway not found", HttpStatus.NOT_FOUND);
            }

            if(!arrival_gateData.isPresent()){
                return new ResponseEntity<>("FlightController: arrival gate not found", HttpStatus.NOT_FOUND);
            }

            if(!departure_gateData.isPresent()){
                return new ResponseEntity<>("FlightController: departure gate not found", HttpStatus.NOT_FOUND);
            }

            if(!discountEventData.isPresent()){
                return new ResponseEntity<>("FlightController: discount event not found", HttpStatus.NOT_FOUND);
            }

            if(!aircraftData.isPresent()){
                return new ResponseEntity<>("FlightController: aircraft not found", HttpStatus.NOT_FOUND);
            }

            Flight flight = flightData.get();
            flight.setAircraft(aircraftData.get());
            flight.setAirway(airwayData.get());
            flight.setArrivalGate(arrival_gateData.get());
            flight.setArrivalTime(request.getArrival_time());
            flight.setDepartureGate(departure_gateData.get());
            flight.setDepartureTime(request.getDeparture_time());
            flight.setStatus(FlightStatus.valueOf(request.getStatus()));
            flight.setDiscount(discountEventData.get());

            Flight _flight = flightRepository.save(flight);

            return new ResponseEntity<>(_flight, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteFlight(@PathVariable("id") long id) {
        try {
            flightRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
