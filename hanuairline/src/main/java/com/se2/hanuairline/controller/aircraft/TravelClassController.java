package com.se2.hanuairline.controller.aircraft;

import com.se2.hanuairline.exception.InvalidInputValueException;
import com.se2.hanuairline.model.aircraft.TravelClass;
import com.se2.hanuairline.payload.aircraft.TravelClassPayload;
import com.se2.hanuairline.repository.aircraft.TravelClassRepository;
import com.se2.hanuairline.service.aircraft.TravelClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/travelClass")
public class TravelClassController {

    @Autowired
    private TravelClassService travelClassService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTravelClass() {
        List<TravelClass> travelClass = travelClassService.getAllTravelClass();
        return new ResponseEntity<>(travelClass, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getTravelClassById(@PathVariable("id") long id) {
        ResponseEntity<?> responseEntity;
        TravelClass travelClass;
        try {
            travelClass = travelClassService.getRecordById(id);
            responseEntity = new ResponseEntity<>(travelClass, HttpStatus.OK);
        } catch (InvalidInputValueException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;

    }

    @PostMapping("/create")
    public ResponseEntity<?> createTravelClass(@Valid @RequestBody TravelClassPayload travelClassPayload) {
        ResponseEntity<?> responseEntity;
        TravelClass result;
        try {
            result = travelClassService.createNewRecord(travelClassPayload);
            responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (InvalidInputValueException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateTravelClass(@PathVariable("id") long id, @RequestBody TravelClassPayload travelClassPayload) {
        ResponseEntity<?> responseEntity;
        TravelClass result;
        try {
            result = travelClassService.updateARecordById(id, travelClassPayload);
            responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (InvalidInputValueException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteATravelClass(@PathVariable("id") long id) {
        ResponseEntity<?> responseEntity;
        TravelClass result ;
        try {
            result= travelClassService.deleteARecordById(id);
            responseEntity = new ResponseEntity<>(result,HttpStatus.OK);
        } catch (InvalidInputValueException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }
}