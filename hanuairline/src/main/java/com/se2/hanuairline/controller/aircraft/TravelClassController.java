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
        return new ResponseEntity<>(travelClass,HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getTravelClassById(@PathVariable("id") long id) {
        ResponseEntity<?> responseEntity;
        TravelClass travelClass;
        try {
            travelClass = travelClassService.getRecordById(id);
            responseEntity = new ResponseEntity<>(travelClass,HttpStatus.OK);
        } catch (InvalidInputValueException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    return responseEntity;

    }

    @PostMapping("/create")
    public ResponseEntity<?> createTravelClass(@RequestBody TravelClassPayload travelClassPayload) {
        ResponseEntity<?> responseEntity;
        TravelClass result ;
        try {
          result =  travelClassService.createNewRecord(travelClassPayload);
          responseEntity = new ResponseEntity<>(result,HttpStatus.OK);
        } catch (InvalidInputValueException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    return responseEntity;
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateTravelClass(@PathVariable("id") long id, @RequestBody TravelClassPayload travelClassPayload) {
//        Optional<TravelClass> travelClassData = travelClassService.findById(id);

//        if (travelClassData.isPresent()) {
//            try {
//                TravelClass _travelClass = (TravelClass) travelClass.clone();
//
//                return new ResponseEntity<>(travelClassRepository.save(_travelClass), HttpStatus.OK);
//            } catch (Exception e){
//                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        travelClassService
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTravelClass(@PathVariable("id") long id) {
        try {
            travelClassRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
