package com.se2.hanuairline.service.airport;

import com.se2.hanuairline.model.airport.Airport;
import com.se2.hanuairline.repository.airport.AirportRepository;
import com.se2.hanuairline.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public Page<Airport> findAll (Long id, String name, String country, String city, String status, int page, int size, String[] sort){
        Pageable pagingSort = PaginationUtils.pagingSort(page, size, sort);

        if(id != null){
            return airportRepository.findById(id, pagingSort);
        }

        if(name.equals("_") && country.equals("_") && city.equals("_") && status.equals("_")){
            return airportRepository.findAll(pagingSort);
        }

        return airportRepository.findByNameContainingAndCountryContainingAndCityContainingAndStatus(name, country, city, status, pagingSort);
    }
}
