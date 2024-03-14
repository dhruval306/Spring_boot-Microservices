package com.microservice.orderservice.Controller;

import com.microservice.orderservice.Dto.OrderRequest;
import com.microservice.orderservice.Service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallBackImple")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
        return CompletableFuture.supplyAsync(() ->orderService.placeOrder(orderRequest));

    }

    public CompletableFuture<String> fallBackImple(OrderRequest orderRequest, RuntimeException runtimeException){
        log.info(String.valueOf(runtimeException));
        return CompletableFuture.supplyAsync(() ->"Oops! Something went wrong");
    }
}
