package com.se2.hanuairline.controller.aircraft;

import com.se2.hanuairline.model.aircraft.AircraftType;
import com.se2.hanuairline.model.aircraft.SeatsByClass;
import com.se2.hanuairline.model.aircraft.TravelClass;
import com.se2.hanuairline.payload.aircraft.SeatsByClassPayLoad;
import com.se2.hanuairline.repository.aircraft.SeatsByClassRepository;
import com.se2.hanuairline.repository.aircraft.AircraftTypeRepository;
import com.se2.hanuairline.repository.aircraft.TravelClassRepository;
import com.se2.hanuairline.service.aircraft.SeatsByClassService;
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
@RequestMapping("/seatsByClass")
public class SeatsByClassController {

    @Autowired
    private SeatsByClassRepository seatsByClassRepository;

    @Autowired
    private AircraftTypeRepository aircraftTypeRepository;

    @Autowired
    private TravelClassRepository travelClassRepository;

    @Autowired
    private SeatsByClassService seatsByClassService;

    private static final Logger logger = LoggerFactory.getLogger(com.se2.hanuairline.controller.user.UserController.class);

    @GetMapping("/getAll")
    public ResponseEntity<? extends Object> getAllSeatsByClass(@RequestParam(required = false) Long aircraft_type_id,
                                                               @RequestParam(required = false) Long travel_class_id,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size,
                                                               @RequestParam(defaultValue = "id,desc") String[] sort) {
        try{
            Page<SeatsByClass> seatsByClassesData = seatsByClassService.getAll(aircraft_type_id, travel_class_id, page, size, sort);

            if(seatsByClassesData.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(seatsByClassesData, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getSeatsByClassById(@PathVariable("id") long id) {
        try{
            Optional<SeatsByClass> seatsByClassData = seatsByClassRepository.findById(id);

            if(!seatsByClassData.isPresent()){
                return new ResponseEntity<>("SeatsByClassController: seat by class not found", HttpStatus.NOT_FOUND);
            }

            SeatsByClass seatsByClass = seatsByClassData.get();

            return new ResponseEntity<>(seatsByClass, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSeatsByClass(@RequestBody SeatsByClassPayLoad request) {
        try {
            Optional<AircraftType> aircraftTypeData = aircraftTypeRepository.findById(request.getAircraftType_id());
            Optional<TravelClass> travelClassData = travelClassRepository.findById(request.getTravelClass_id());

            if(!aircraftTypeData.isPresent()){
                return new ResponseEntity<>("SeatsByClassController: aircraft type not found", HttpStatus.NOT_FOUND);
            }

            if(!travelClassData.isPresent()){
                return new ResponseEntity<>("SeatsByClassController: travel class type not found", HttpStatus.NOT_FOUND);
            }

            SeatsByClass seatsByClass = new SeatsByClass();

            SeatsByClass _seatsByClass = seatsByClassRepository.save(seatsByClass);

            return new ResponseEntity<>(_seatsByClass, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateSeatsByClass(@PathVariable("id") long id, @RequestBody SeatsByClassPayLoad request) {
        try {
            Optional<SeatsByClass> seatsByClassData = seatsByClassRepository.findById(request.getId());
            Optional<AircraftType> aircraftTypeData = aircraftTypeRepository.findById(request.getAircraftType_id());
            Optional<TravelClass> travelClassData = travelClassRepository.findById(request.getTravelClass_id());

            if(!seatsByClassData.isPresent()){
                return new ResponseEntity<>("SeatsByClassController: seat by class not found", HttpStatus.NOT_FOUND);
            }

            if(!aircraftTypeData.isPresent()){
                return new ResponseEntity<>("SeatsByClassController: aircraft type not found", HttpStatus.NOT_FOUND);
            }

            if(!travelClassData.isPresent()){
                return new ResponseEntity<>("SeatsByClassController: travel class type not found", HttpStatus.NOT_FOUND);
            }

            SeatsByClass seatsByClass = seatsByClassData.get();
            AircraftType aircraftType = aircraftTypeData.get();
            TravelClass travelClass = travelClassData.get();
            seatsByClass.setAircraftType(aircraftType);
            seatsByClass.setTravelClass(travelClass);
            seatsByClass.setQuantity(request.getQuantity());
            seatsByClass.setRows_quantity(request.getRows_quantity());
            seatsByClass.setSeats_per_row(request.getSeats_per_row());

            SeatsByClass _seatsByClass = seatsByClassRepository.save(seatsByClass);

            return new ResponseEntity<>(_seatsByClass, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Dont use this
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSeatsByClass(@PathVariable("id") long id) {
        try {
            seatsByClassRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
