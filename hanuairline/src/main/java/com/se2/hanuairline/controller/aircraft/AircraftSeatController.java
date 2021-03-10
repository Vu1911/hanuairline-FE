package com.se2.hanuairline.controller.aircraft;

import com.se2.hanuairline.model.aircraft.Aircraft;
import com.se2.hanuairline.model.aircraft.AircraftSeat;
import com.se2.hanuairline.model.aircraft.TravelClass;
import com.se2.hanuairline.payload.aircraft.AircraftSeatPayload;
import com.se2.hanuairline.repository.aircraft.AircraftSeatRepository;
import com.se2.hanuairline.repository.aircraft.AircraftRepository;
import com.se2.hanuairline.repository.aircraft.TravelClassRepository;
import com.se2.hanuairline.service.aircraft.AircraftSeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aircraftSeat")
public class AircraftSeatController {

    @Autowired
    private AircraftSeatRepository aircraftSeatRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private TravelClassRepository travelClassRepository;

    @Autowired
    private AircraftSeatService aircraftSeatService;

    private static final Logger logger = LoggerFactory.getLogger(com.se2.hanuairline.controller.user.UserController.class);

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAircraftSeat(@RequestParam(required = false) Long aircraft_id,
                                                @RequestParam(required = false) Long travelclass_id,
                                                @RequestParam(defaultValue =  "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id,desc") String[] sort) {
        try{
            Page<AircraftSeat> aircraftSeatsData = aircraftSeatService.findAll(aircraft_id, travelclass_id, page, size, sort);

            if(aircraftSeatsData.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(aircraftSeatsData, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getAircraftSeatById(@PathVariable("id") long id) {
        Optional<AircraftSeat> aircraftSeatData = aircraftSeatRepository.findById(id);

        if (aircraftSeatData.isPresent()) {
            AircraftSeat aircraftSeat = aircraftSeatData.get();

            return new ResponseEntity<>(aircraftSeat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAircraftSeat(@RequestBody AircraftSeatPayload request) {
        try {
            Optional<Aircraft> aircraftData = aircraftRepository.findById(request.getAircraft_id());
            Optional<TravelClass> travelClassData = travelClassRepository.findById(request.getTravelClass_id());

            if(!aircraftData.isPresent()){
                return new ResponseEntity<>("AircraftSeatController: aircraft not found", HttpStatus.NOT_FOUND);
            }

            if(!travelClassData.isPresent()){
                return new ResponseEntity<>("AircraftSeatController: travel class not fount", HttpStatus.NOT_FOUND);
            }

            AircraftSeat aircraftSeat = new AircraftSeat();
            Aircraft aircraft = aircraftData.get();
            TravelClass travelClass = travelClassData.get();

            aircraftSeat.setAircraft(aircraft);
            aircraftSeat.setTravelClass(travelClass);

            AircraftSeat _aircraftSeat = aircraftSeatRepository.save(aircraftSeat);

            return new ResponseEntity<>(_aircraftSeat, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateAircraftSeat(@PathVariable("id") long id, @RequestBody AircraftSeatPayload request) {
        try {
            Optional<AircraftSeat> aircraftSeatData = aircraftSeatRepository.findById(request.getId());
            Optional<Aircraft> aircraftData = aircraftRepository.findById(request.getAircraft_id());
            Optional<TravelClass> travelClassData = travelClassRepository.findById(request.getTravelClass_id());

            if(!aircraftSeatData.isPresent()){
                return new ResponseEntity<>("AircraftSeatController: aircraft seat not found", HttpStatus.NOT_FOUND);
            }

            if(!aircraftData.isPresent()){
                return new ResponseEntity<>("AircraftSeatController: aircraft not found", HttpStatus.NOT_FOUND);
            }

            if(!travelClassData.isPresent()){
                return new ResponseEntity<>("AircraftSeatController: travel class not fount", HttpStatus.NOT_FOUND);
            }

            AircraftSeat aircraftSeat = aircraftSeatData.get();
            Aircraft aircraft = aircraftData.get();
            TravelClass travelClass = travelClassData.get();

            aircraftSeat.setAircraft(aircraft);
            aircraftSeat.setTravelClass(travelClass);

            AircraftSeat _aircraftSeat = aircraftSeatRepository.save(aircraftSeat);

            return new ResponseEntity<>(_aircraftSeat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Dont use this!!!
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAircraftSeat(@PathVariable("id") long id) {
        try {
            aircraftSeatRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
