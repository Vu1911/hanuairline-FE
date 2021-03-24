package com.se2.hanuairline.service;



import com.se2.hanuairline.exception.InvalidInputValueException;
import com.se2.hanuairline.model.PriceByClass;
import com.se2.hanuairline.model.aircraft.Aircraft;
import com.se2.hanuairline.model.aircraft.TravelClass;
import com.se2.hanuairline.model.airport.Airway;
import com.se2.hanuairline.payload.PriceByClassPayload;
import com.se2.hanuairline.repository.PriceByClassRepository;
import com.se2.hanuairline.repository.aircraft.AircraftRepository;
import com.se2.hanuairline.repository.aircraft.TravelClassRepository;
import com.se2.hanuairline.repository.airport.AirwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceByClassService {
    @Autowired
    private AirwayRepository airwayRepository;
    @Autowired
    private PriceByClassRepository priceByClassRepository;

    @Autowired
    private TravelClassRepository travelClassRepository;

    // get all  finished
    public List<PriceByClass> getAllPriceByClass() {
        List<PriceByClass> getList =  priceByClassRepository.findAll();

        return getList;
    }
    // get one by travelclass id and airway id -> finished
    public PriceByClass getOnePriceByClass(Long travelClassId, Long airwayId){
        Optional<PriceByClass> priceByClassData = priceByClassRepository.findByTravelClass_IdAndAirway_Id(travelClassId,airwayId);
        return priceByClassData.get();
    }

    // update one
    public PriceByClass updateARecord(PriceByClassPayload priceByClassPayload) throws InvalidInputValueException {
//        PriceByClass priceByClass = priceByClassRepository.getOne(priceByClassDTO.getId());
        // kiem tra co travelClass khong
        Optional<TravelClass> travelClass=travelClassRepository.findById(priceByClassPayload.getTravelclass_id());
        Optional<Airway> airway=airwayRepository.findById(priceByClassPayload.getTravelclass_id());

        if (!checkExisted(priceByClassPayload)){
              throw new InvalidInputValueException("record does not exist "+"id :"+priceByClassPayload.getId()
              );
         }
         if(!travelClass.isPresent()){
             throw new InvalidInputValueException("Invalid update travelclass id"+priceByClassPayload.getTravelclass_id());
         }
        if(!airway.isPresent()){
            throw new InvalidInputValueException("Invalid update airway id"+priceByClassPayload.getTravelclass_id());
        }
        Optional<PriceByClass> priceByClassData = priceByClassRepository.findById(priceByClassPayload.getId());
        PriceByClass priceByClass = priceByClassData.get();
        priceByClass.setTravelClass(travelClass.get());
        priceByClass.setAirway(airway.get());
        PriceByClass result=   priceByClassRepository.save(priceByClass);

        return result;

    }
    // update many
    public List<PriceByClass> updateManyRecords(List<PriceByClassPayload> priceByClassPayloads) throws InvalidInputValueException {
       List<PriceByClass> updatedRecords = new ArrayList<>();
        String fails = "";

        for(PriceByClassPayload item : priceByClassPayloads ){
        try {
            PriceByClass priceByClass = updateARecord(item);
            updatedRecords.add(priceByClass);
        }catch(Exception e){
            fails.concat(" "+e.getMessage());
            continue;
        }

        }
        if(!fails.equals("")){
            throw new InvalidInputValueException(fails);
        }
        return updatedRecords;

    }
    // add one

    public PriceByClass insertNewRecord(PriceByClassPayload priceByClassPayload) throws InvalidInputValueException {
        // find if existed ? -> id used
        if(this.checkExisted(priceByClassPayload)) throw new InvalidInputValueException("Invalid PriceByClass_id or duplicated a record content");
        Long travelClassId = priceByClassPayload.getTravelclass_id();
        Long aircraftId = priceByClassPayload.getAirway_id();
        PriceByClass priceByClass = new PriceByClass();
        priceByClass.getAirway().setId(aircraftId);
        priceByClass.getTravelClass().setId(aircraftId);
        priceByClass.setPrice(priceByClass.getPrice());
        priceByClass= priceByClassRepository.save(priceByClass);



        return priceByClass;

    }

    // delete many
    public List<PriceByClass> deleteManyRecords(Long[]ids) throws InvalidInputValueException {
        List<PriceByClass> deletedRecords = new ArrayList<>();
        String fails="can not delete records with ids: ";
    for(Long id : ids){
     Optional<PriceByClass> priceByClass= priceByClassRepository.findById(id);
        if(priceByClass.isPresent()){
            deletedRecords.add(priceByClass.get());
            priceByClassRepository.deleteById(id);
        }else{
            fails.concat(" "+id);
        }
    }
    if(!fails.equals("")){
        throw new InvalidInputValueException(fails);
    }


    return deletedRecords;

    }

    public PriceByClass deleteOneRecord(Long id) throws InvalidInputValueException {
        PriceByClass priceByClass;
        Optional<PriceByClass>priceByClassData=priceByClassRepository.findById(id);
        if(priceByClassData.isPresent()){
            priceByClass = priceByClassData.get();
            priceByClassRepository.deleteById(id);
        }
        else{
            throw new InvalidInputValueException("Not exist a record with this id :"+id);
        }
        return priceByClass;
    }
     // exist a row
    private boolean checkExisted(PriceByClassPayload priceByClassPayload){
        //Optional<TravelClass> travelClassData = travelClassService.findById(priceByClassDT).getTravelClassId);
       Optional<PriceByClass> idDuplicated= priceByClassRepository.findById(priceByClassPayload.getId());
     Optional<PriceByClass> duplicatedRecord= priceByClassRepository.findByTravelClass_IdAndAirway_Id(priceByClassPayload.getTravelclass_id(), priceByClassPayload.getAirway_id());
      return (idDuplicated.isPresent()||duplicatedRecord.isPresent()) ;


    }


}

