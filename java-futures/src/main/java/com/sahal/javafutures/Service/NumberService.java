package com.sahal.javafutures.Service;

//import com.sahal.javafutures.Feign.FeignService;
import com.sahal.javafutures.Model.NumberModel;
import com.sahal.javafutures.Repository.NumberRepository;
import com.sahal.javafutures.ValueObject.ValueObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

@Service
@Slf4j
public class NumberService {

    @Autowired
    private NumberRepository numberRepository;

    @Autowired
    private NumberSquareService numberSquareService;

//    @Autowired
//    private FeignService feignService;

    private ExecutorService executor = Executors.newFixedThreadPool(10);
//    private List<Long> parseCSVFile(MultipartFile file) throws Exception{
//        final List<Long> numberList = new ArrayList<>();
//        try {
//            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    long number = Long.parseLong(line);
//                    numberList.add(number);
//                }
//                return numberList;
//            }
//        }
//        catch (Exception ex){
//            log.error("Failed to parse CSV file "+ex.getMessage());
//            throw ex;
//        }
//    }

    public List<NumberModel> getNumbersThroughFile(MultipartFile file) throws Exception {
            List<Long> numberList = new ArrayList<>();
            NumberModel squareNumber;
            int count = 0;
            try {
                try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        long number = Long.parseLong(line);
                        //numberList.add(number);
                        //squareNumber = feignService.getAllSquares(number).getBody();
                        //numberRepository.save(squareNumber);
                        numberList.add(number);
                    }
//                numberList.forEach(x-> {
//                    numberRepository.save(x);
//                });
                    List<CompletableFuture<NumberModel>> futureList = new ArrayList<>();
                    List<ValueObject> valueObjectsList = new ArrayList<>();
                    //numberRepository.saveAll(numberList);
                    for (long num : numberList) {
                        ValueObject valueObject = getSquaredModel(num);
                        valueObjectsList.add(valueObject);
                    }
                    List<NumberModel> squaredModel = new ArrayList<>();
                    for (ValueObject vo : valueObjectsList) {
                        NumberModel numberModel = vo.getNumberModel().get();
                        boolean flag = vo.isSaveInDatabase();
                        if (flag) {
                            numberRepository.save(numberModel);
                            count++;
                        }
                        squaredModel.add(numberModel);
                    }
                    log.info("Total values saved in database = "+count);
                    return squaredModel;
                }
            }
                //List<Long> numberList = parseCSVFile(file);
                //log.info("Saving list of employees of size " + employeeList.size() + " " + Thread.currentThread().getName());
                //employeeRepository.saveAll(employeeList);
                //String message = "Data saved successfully!";
                //return numberList;

            catch (Exception ex) {
                log.error("Exception caught " + ex.getMessage());
                throw ex;
            }

    }

    private ValueObject getSquaredModel(long num) throws ExecutionException, InterruptedException {
        boolean flag = false;
        CompletableFuture<NumberModel> future1 = CompletableFuture.supplyAsync(()-> numberRepository.findById(num).orElse(null), executor);
        if (future1.get() == null){
            future1 = CompletableFuture.supplyAsync(
                    ()-> numberSquareService.findSquareOfANumber(num), executor);
            flag = true;
        }
        ValueObject vo = new ValueObject();
        vo.setNumberModel(future1);
        vo.setSaveInDatabase(flag);
        return vo;
    }

    private NumberModel getSquare(long num){

        CompletableFuture<NumberModel> futureSearch = new CompletableFuture<>();
        CompletableFuture<NumberModel> futureCalculate = new CompletableFuture<>();
        CompletableFuture.runAsync(()-> {
            Optional<NumberModel> numberModel = numberRepository.findById(num);
            if (numberModel.isPresent()){
                futureSearch.complete(numberModel.get());
            }
            else {
                futureCalculate.complete(null);
            }
        });
        return futureCalculate.thenApplyAsync(()-> {
            futureCalculate = numberSquareService.findSquareOfANumber(num);
        }, executor);
    }
}
