package com.microservice.orderservice.Controller;

import com.microservice.orderservice.Dto.OrderRequest;
import com.microservice.orderservice.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public List<OrderResponse> getOrders(){
//
//    }
}
