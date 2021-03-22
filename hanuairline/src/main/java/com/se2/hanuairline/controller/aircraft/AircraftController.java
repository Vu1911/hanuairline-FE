package com.se2.hanuairline.controller.aircraft;

import com.se2.hanuairline.model.aircraft.Aircraft;
import com.se2.hanuairline.model.aircraft.AircraftStatus;
import com.se2.hanuairline.model.aircraft.AircraftType;
import com.se2.hanuairline.payload.aircraft.AircraftPayload;
import com.se2.hanuairline.repository.aircraft.AircraftRepository;
import com.se2.hanuairline.repository.aircraft.AircraftTypeRepository;
import com.se2.hanuairline.service.aircraft.AircraftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AircraftTypeRepository aircraftTypeRepository;

    @Autowired
    private AircraftService aircraftService;

    private static final Logger logger = LoggerFactory.getLogger(com.se2.hanuairline.controller.user.UserController.class);

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAirCraft(@RequestParam(required = false) String name,
                                            @RequestParam(required = false) String status,
                                            @RequestParam(required = false) Long aircraft_type_id,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id,desc") String[] sort) {
        try{
            Page<Aircraft> aircraftsData = aircraftService.findAll(name, status, aircraft_type_id, page, size, sort);

            if(aircraftsData.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(aircraftsData, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getAircraftById(@PathVariable("id") long id) {
        Aircraft aircraft = aircraftService.getAircraftById(id);

        if (aircraft != null) {
            return new ResponseEntity<>(aircraft, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAircraft(@RequestBody AircraftPayload request) {
        try {
            Aircraft _aircraft = aircraftService.createAircraft(request);

            if (_aircraft == null) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(_aircraft, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateAircraft(@PathVariable("id") long id, @RequestBody AircraftPayload request) {
        Optional<Aircraft> aircraftData = aircraftRepository.findById(id);
        Optional<AircraftType> aircraftTypeData = aircraftTypeRepository.findById(request.getAircraft_type_id());

        if (!aircraftData.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AircraftController: aircraft not found");
        }

        if (!aircraftTypeData.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AircraftController: aircraft type not found");
        }

        try {
            Aircraft _aircraft = aircraftData.get();

            if(!_aircraft.getStatus().equals(AircraftStatus.ACTIVATED)){
                _aircraft.setName(request.getName());
                _aircraft.setStatus(AircraftStatus.valueOf(request.getStatus()));
                _aircraft.setAircraftType(aircraftTypeData.get());

                Aircraft aircraft = aircraftRepository.save(_aircraft);

                return new ResponseEntity<>(aircraft, HttpStatus.OK);
            }

            // if an aircraft's being updated from ACTIVATED to other, make sure it is not assigned any flight from now
            // or else, require the replaced flight for those flight
            // NOT IMPLEMENTED !!!!
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAircraft(@PathVariable("id") long id) {
        try {
            Optional<Aircraft> aircraftData = aircraftRepository.findById(id);

            if(!aircraftData.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AircraftController: aircraft not found");
            }

            Aircraft _aircraft = aircraftData.get();

            if(!_aircraft.getStatus().equals(AircraftStatus.DEACTIVATED)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("AircraftController: aircraft not in deactivated status");
            }

            aircraftRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}