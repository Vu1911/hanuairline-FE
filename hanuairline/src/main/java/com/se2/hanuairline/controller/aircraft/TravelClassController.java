package com.se2.hanuairline.controller.aircraft;

import com.se2.hanuairline.model.aircraft.TravelClass;
import com.se2.hanuairline.repository.aircraft.TravelClassRepository;
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
    private TravelClassRepository travelClassRepository;

    private static final Logger logger = LoggerFactory.getLogger(com.se2.hanuairline.controller.user.UserController.class);

    @GetMapping("/getAll")
    public List<TravelClass> getAllTravelClass() {
        List<TravelClass> travelClasss = travelClassRepository.findAll();

        return travelClasss;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getTravelClassById(@PathVariable("id") long id) {
        Optional<TravelClass> travelClass = travelClassRepository.findById(id);

        if (travelClass.isPresent()) {
            return new ResponseEntity<>(travelClass.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createtravelClass(@RequestBody TravelClass travelClass) {
        try {
            TravelClass clone = (TravelClass) travelClass.clone();
            TravelClass _travelClass = travelClassRepository
                    .save(clone);
            return new ResponseEntity<>(_travelClass, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateTravelClass(@PathVariable("id") long id, @RequestBody TravelClass travelClass) {
        Optional<TravelClass> travelClassData = travelClassRepository.findById(id);

        if (travelClassData.isPresent()) {
            try {
                TravelClass _travelClass = (TravelClass) travelClass.clone();

                return new ResponseEntity<>(travelClassRepository.save(_travelClass), HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
