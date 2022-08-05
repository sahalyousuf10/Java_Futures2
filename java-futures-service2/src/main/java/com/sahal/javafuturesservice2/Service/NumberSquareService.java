package com.sahal.javafuturesservice2.Service;

import com.sahal.javafuturesservice2.Dto.NumberSquareDto;
//import com.sahal.javafuturesservice2.Feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class NumberSquareService {

//    @Autowired
//    private FeignService feignService;

    private ExecutorService executor = Executors.newFixedThreadPool(10);

    public Future<NumberSquareDto> getSquareOfNumbers(long num) {
        return executor.submit(() -> {
            List<Long> numberList;
            NumberSquareDto numberSquareDto = new NumberSquareDto();
            numberSquareDto.setNumber(num);
            numberSquareDto.setSquare(num*num);
            //numberList = feignService.getAllNumbers(file).getBody();
//        List<NumberSquareDto> numberSquareDtoList = new ArrayList<>();
//        for (Long x : numberList) {
//            numberSquareDto.setNumber(x);
//            long l = x * x;
//            numberSquareDto.setSquare(l);
//            numberSquareDtoList.add(numberSquareDto);
//        }
            return numberSquareDto;
        });
    }
}
