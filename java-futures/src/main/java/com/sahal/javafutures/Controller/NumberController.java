package com.sahal.javafutures.Controller;

import com.sahal.javafutures.Model.NumberModel;
import com.sahal.javafutures.Service.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class NumberController {

    @Autowired
    private NumberService numberService;

    @GetMapping(value = "/numbers", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity<List<NumberModel>> getNumbersThroughFile(
            @RequestParam(value = "files") MultipartFile[] files) throws Exception{
        //String result = null;
        List<NumberModel> numberList = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                numberList = numberService.getNumbersThroughFile(file);
            }
        }
        catch (Exception ex){
            log.error("Exception caught "+ex.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(numberList);
    }
}
