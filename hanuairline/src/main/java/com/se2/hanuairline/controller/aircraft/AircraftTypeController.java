package com.se2.hanuairline.controller.aircraft;

import com.se2.hanuairline.model.aircraft.AircraftType;
import com.se2.hanuairline.repository.aircraft.AircraftTypeRepository;
import com.se2.hanuairline.util.PaginationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/aircraftType")
public class AircraftTypeController {

    @Autowired
    private AircraftTypeRepository aircraftTypeRepository;

    private static final Logger logger = LoggerFactory.getLogger(com.se2.hanuairline.controller.user.UserController.class);

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAircraftType(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id,desc") String[] sort) {
        try {
            Pageable pagingSort = PaginationUtils.pagingSort(page, size, sort);
            Page<AircraftType> aircraftTypes = aircraftTypeRepository.findAll(pagingSort);

            if(aircraftTypes.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(aircraftTypes, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getAircraftTypeById(@PathVariable("id") long id) {
        Optional<AircraftType> aircraftType = aircraftTypeRepository.findById(id);

        if (aircraftType.isPresent()) {
            return new ResponseEntity<>(aircraftType.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    public ResponseEntity<?> createAircraftType(@Valid @RequestBody AircraftType aircraftType) {
        try {
            AircraftType clone = (AircraftType) aircraftType.clone();
            AircraftType _aircraftType = aircraftTypeRepository
                    .save(clone);
            return new ResponseEntity<>(_aircraftType, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateAircraftType(@PathVariable("id") long id, @RequestBody AircraftType aircraftType) {
        Optional<AircraftType> aircraftTypeData = aircraftTypeRepository.findById(id);

        if (aircraftTypeData.isPresent()) {
            try {
                return new ResponseEntity<>(null, HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAircraftType(@PathVariable("id") long id) {
        try {
            aircraftTypeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
