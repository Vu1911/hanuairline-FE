package com.se2.hanuairline.service.airport;

import com.se2.hanuairline.model.airport.Airway;
import com.se2.hanuairline.repository.airport.AirwayRepository;
import com.se2.hanuairline.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AirwayService {

    @Autowired
    private AirwayRepository airwayRepository;

    public Page<Airway> findAll (int page, int size, String[] sort){
        Pageable pagingSort = PaginationUtils.pagingSort(page, size, sort);

        return airwayRepository.findAll(pagingSort);
    }

    public Airway findByArrivalAirportAndDepartureAirport (String arrivalAirport, String departureAirport){
        Airway airwayData = airwayRepository.findDistinctByArrivalAirport_NameAndAndDepartureAirport_Name(arrivalAirport, departureAirport);

        return airwayData;
    }
}
