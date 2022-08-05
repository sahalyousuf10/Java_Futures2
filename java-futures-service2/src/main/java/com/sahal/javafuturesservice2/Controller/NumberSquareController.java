package com.sahal.javafuturesservice2.Controller;

import com.sahal.javafuturesservice2.Dto.NumberSquareDto;
import com.sahal.javafuturesservice2.Service.NumberSquareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class NumberSquareController {

    @Autowired
    private NumberSquareService numberSquareService;

//    @GetMapping(value = "/square", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
//    public ResponseEntity<List<NumberSquareDto>> getSquareOfNumbers(@RequestParam(value = "files") MultipartFile[] files){
//        //String result = null;
//        List<NumberSquareDto> squareOfNumbers = new ArrayList<>();
//        try {
//            for (MultipartFile file : files) {
//                squareOfNumbers = numberSquareService.getSquareOfNumbers(file).get();
//            }
//        }
//        catch (Exception ex){
//            log.error("Exception caught "+ex.getMessage());
//        }
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(squareOfNumbers);
//    }

    @GetMapping("/square/{num}")
    public ResponseEntity<NumberSquareDto> getSquareOfNumbers(@PathVariable long num) throws ExecutionException, InterruptedException {
        NumberSquareDto numberSquareDto = numberSquareService.getSquareOfNumbers(num).get();
        return ResponseEntity.ok(numberSquareDto);
    }
}
