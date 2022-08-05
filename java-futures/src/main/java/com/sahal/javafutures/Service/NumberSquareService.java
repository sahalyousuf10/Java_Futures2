package com.sahal.javafutures.Service;

import com.sahal.javafutures.Model.NumberModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NumberSquareService {

    public NumberModel findSquareOfANumber(long number){
        log.info("Calculating Square of a number: "+number);
        NumberModel numberModel = new NumberModel();
        long squared = number*number;
        numberModel.setNumber(number);
        numberModel.setSquare(squared);
        return numberModel;
    }
}
