package com.se2.hanuairline.controller;

import com.se2.hanuairline.exception.InvalidInputValueException;
import com.se2.hanuairline.model.PriceByClass;
import com.se2.hanuairline.payload.PriceByClassPayload;
import com.se2.hanuairline.service.PriceByClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


    @RestController
    @RequestMapping("/priceByClass")
    public class PriceByClassController {
        // controller mapping request / gọi service , trả data về dạng json
        // mapping request
        @Autowired
        private PriceByClassService priceByClassService;


        // Read all finished
        @GetMapping("/allRecords")
        public ResponseEntity<?> showAllPriceByClass(){
            priceByClassService = new PriceByClassService();
            List<PriceByClass> result= priceByClassService.getAllPriceByClass();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        // Read one based on the travelclassid and airwayid or by id -> finished
        @GetMapping("/get-one")
        public ResponseEntity<?> showOnePriceByClass(@RequestParam("travelclass_id") Long travelClassId,@RequestParam("airway_id") Long airwayId ){
            priceByClassService = new PriceByClassService();
            PriceByClass result = priceByClassService.getOnePriceByClass(travelClassId,airwayId);
            return new ResponseEntity<>(result,HttpStatus.OK);

        }
        // add One -> finished
        @PostMapping("/insert")
        public ResponseEntity<?> insertPriceByClass(@RequestBody PriceByClassPayload priceByClassPayload){
            priceByClassService = new PriceByClassService();
            PriceByClass result= null;
            ResponseEntity<?> responseEntity;
            try {
                result = priceByClassService.insertNewRecord(priceByClassPayload);
             // after insert success
                responseEntity = new ResponseEntity<>(result,HttpStatus.OK);
            } catch (InvalidInputValueException e) {
                // insert failed
                responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
            }

        return responseEntity;
        }

        // update one ->finished
        @PutMapping("/update-one")
        public ResponseEntity<?> updateOneRecord(@RequestBody PriceByClassPayload priceByClassPayload) { // phai gui content update len nua
            ResponseEntity<?> responseEntity ;

                try {
                    PriceByClass result= priceByClassService.updateARecord(priceByClassPayload);
                    responseEntity = new ResponseEntity<>(result,HttpStatus.OK);
            } catch (InvalidInputValueException e) {
                    responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
            }
            return responseEntity;

        }



        // update many -> finished
        @PutMapping("/update-many")
        public ResponseEntity<?> updateManyRecords(@RequestBody List<PriceByClassPayload> priceByClassPayloads){
            // String --------------------------------
            ResponseEntity<?> responseEntity;
            List<PriceByClass> result ;
            try {
                result = priceByClassService.updateManyRecords(priceByClassPayloads);
                responseEntity = new ResponseEntity<>(result,HttpStatus.OK);
            } catch (InvalidInputValueException e) {
                responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
            }
            return responseEntity;
        }


        // delete many records
        @DeleteMapping("/delete-many")
        public ResponseEntity<?> deleteManyPriceByClass(@RequestParam("ids") Long[]ids){
            ResponseEntity<?> responseEntity;
            List<PriceByClass> result= null;
            try {
                result = priceByClassService.deleteManyRecords(ids);
                responseEntity = new ResponseEntity<>(result,HttpStatus.OK);
            } catch (InvalidInputValueException e) {
                responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
            }
            return responseEntity;

        }
        // delete a record -> finished
        @DeleteMapping("/delete-one/{id}")
        public ResponseEntity<?> deleteAPriceByClass(@PathVariable("id") Long id){
            ResponseEntity<?> responseEntity;
            PriceByClass priceByClass=null;
            try {
                 priceByClass = priceByClassService.deleteOneRecord(id);
                 responseEntity = new ResponseEntity<>(priceByClass,HttpStatus.OK);
            } catch (InvalidInputValueException e) {
                responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
            }
            return responseEntity;
        }

    }


