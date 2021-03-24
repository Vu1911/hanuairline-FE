package com.se2.hanuairline.service;

import com.se2.hanuairline.model.DiscountEvent;
import com.se2.hanuairline.payload.DiscountEventPayload;
import com.se2.hanuairline.repository.DiscountEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountEventService {

    @Autowired
    private DiscountEventRepository discountEventRepository;

    public List<DiscountEvent> getAllDiscountEvent(){
        return discountEventRepository.findAll();
    }

    public DiscountEvent getById(Long id){
        Optional<DiscountEvent> discountEvent = discountEventRepository.findById(id);

        if (discountEvent.isPresent()) {
            return discountEvent.get();
        } else {
            return null;
        }
    }

    public DiscountEvent createDiscountEvent(DiscountEventPayload request){
        DiscountEvent discountEvent = new DiscountEvent();

//        discountEvent;
        return null;
    }
}
