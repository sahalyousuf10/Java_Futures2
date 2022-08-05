//package com.sahal.javafuturesservice2.Feign;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@FeignClient(name = "service1",
//            url = "http://localhost:9088")
//public interface FeignService {
//
//    @GetMapping(value = "/numbers", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
//    ResponseEntity<List<Long>> getAllNumbers(@RequestParam MultipartFile files);
//}
