//package com.sahal.javafutures.Feign;
//
//import com.sahal.javafutures.Model.NumberModel;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(name = "service2",
//             url = "http://localhost:9089")
//public interface FeignService {
//
//    @GetMapping("/square/{num}")
//    ResponseEntity<NumberModel> getAllSquares (@PathVariable long num);
//}
