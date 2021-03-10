package com.se2.hanuairline.controller;

import com.se2.hanuairline.model.DiscountEvent;
import com.se2.hanuairline.repository.DiscountEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/discountEvent")
public class DiscountEventController {

    @Autowired
    private DiscountEventRepository discountEventRepository;

    private static final Logger logger = LoggerFactory.getLogger(com.se2.hanuairline.controller.user.UserController.class);

    @GetMapping("/getAll")
    public List<DiscountEvent> getAllDiscountEvent(@RequestParam(required = false) String tittle) {
        List<DiscountEvent> discountEvents = discountEventRepository.findAll();

        return discountEvents;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getDiscountEventById(@PathVariable("id") long id) {
        Optional<DiscountEvent> discountEvent = discountEventRepository.findById(id);

        if (discountEvent.isPresent()) {
            return new ResponseEntity<>(discountEvent.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDiscountEvent(@RequestBody DiscountEvent discountEvent) {
        try {
            DiscountEvent clone = (DiscountEvent) discountEvent.clone();
            DiscountEvent _discountEvent = discountEventRepository
                    .save(clone);
            return new ResponseEntity<>(_discountEvent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateDiscountEvent(@PathVariable("id") long id, @RequestBody DiscountEvent discountEvent) {
        Optional<DiscountEvent> discountEventData = discountEventRepository.findById(id);

        if (discountEventData.isPresent()) {
            try {
                DiscountEvent _discountEvent = (DiscountEvent) discountEvent.clone();

                return new ResponseEntity<>(discountEventRepository.save(_discountEvent), HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDiscountEvent(@PathVariable("id") long id) {
        try {
            discountEventRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
