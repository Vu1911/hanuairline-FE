package com.se2.hanuairline.service.aircraft;

import com.se2.hanuairline.model.aircraft.TravelClass;
import com.se2.hanuairline.repository.aircraft.TravelClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TravelClassService {

    @Autowired
    private TravelClassRepository travelClassRepository;

    public TravelClass findById (Long id){
        Optional<TravelClass> travelClassData = travelClassRepository.findById(id);

        if(travelClassData.isPresent()){
            return travelClassData.get();
        }
        return null;
    }

}
