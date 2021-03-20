package com.se2.hanuairline.service.aircraft;

import com.se2.hanuairline.model.aircraft.Aircraft;
import com.se2.hanuairline.model.aircraft.AircraftStatus;
import com.se2.hanuairline.model.aircraft.AircraftType;
import com.se2.hanuairline.payload.aircraft.AircraftPayload;
import com.se2.hanuairline.repository.aircraft.AircraftRepository;
import com.se2.hanuairline.repository.aircraft.AircraftTypeRepository;
import com.se2.hanuairline.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {

    @Autowired
    AircraftRepository aircraftRepository;

    @Autowired
    AircraftTypeRepository aircraftTypeRepository;

    @Autowired
    AircraftSeatService aircraftSeatService;

    public Page<Aircraft> findAll(String name, String status, Long aircraft_type_id, int page, int size, String[] sort){
        Pageable pagingSort = PaginationUtils.pagingSort(page, size, sort);

        Page<Aircraft> aircrafts = null;

        if (name == null && status == null && aircraft_type_id == null){
            return aircraftRepository.findAll(pagingSort);
        }

        return aircraftRepository.findByNameContainingOrStatusOrAircraftType_Id(name, status, aircraft_type_id, pagingSort);
    }

    public Aircraft getAircraftById (Long id) {
        Optional<Aircraft> aircraft = aircraftRepository.findById(id);

        if (aircraft.isPresent()) {
            Aircraft _aircraft = aircraft.get();

            return _aircraft;
        } else {
            return null;
        }
    }

    public Aircraft createAircraft (AircraftPayload request){
        Optional<AircraftType> aircraftTypeData = aircraftTypeRepository.findById(request.getAircraft_type_id());

        if (!aircraftTypeData.isPresent()) {
            return null;
        }

        Aircraft aircraft = new Aircraft();
        aircraft.setName(request.getName());
        aircraft.setStatus(AircraftStatus.valueOf(request.getStatus()));
        aircraft.setAircraftType(aircraftTypeData.get());

        Aircraft _aircraft = aircraftRepository
                .save(aircraft);;

        // create AircraftSeat
        if(!aircraftSeatService.createAircraftSeat(aircraft)){
            return null; // even though air
        }

        return _aircraft;
    }

    public Aircraft findById (Long id){
        Optional<Aircraft> aircraftData = aircraftRepository.findById(id);

        if(aircraftData.isPresent()){
            return aircraftData.get();
        }

        return null;
    }

    // get all the airplanes which are available at a specific date
    // first get all the flights that are distinct of the aircraft_id and sort it from the latest departure date
    public List<Aircraft> getAvailableAircraftByTime(Instant time){
        List<Aircraft> availableAircraft = null;

        return availableAircraft;
    }

    // check aircraft availability
    //
    public boolean checkAircraftByTimeAndAirport(Aircraft plane, Instant time, Long airport_id){
        return false;
    }

}
