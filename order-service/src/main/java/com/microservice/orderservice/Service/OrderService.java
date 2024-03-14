package com.microservice.orderservice.Service;

import com.microservice.orderservice.Dto.InventoryResponse;
import com.microservice.orderservice.Dto.OrderLineItemsRequest;
import com.microservice.orderservice.Dto.OrderRequest;
import com.microservice.orderservice.Model.Order;
import com.microservice.orderservice.Model.OrderLineItems;
import com.microservice.orderservice.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;

    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsRequest()
                .stream().map(this::mapToOrder)
                .toList();
        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();

        InventoryResponse[] inventoryResponseArray = webClient.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

        if(allProductsInStock){
            orderRepository.save(order);
            return "Order Placed";
        }else {
            throw new IllegalStateException("Product is not in stock, please try again");
        }

    }

    private OrderLineItems mapToOrder(OrderLineItemsRequest orderLineItemsRequest) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsRequest.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsRequest.getQuantity());
        orderLineItems.setPrice(orderLineItemsRequest.getPrice());
        return orderLineItems;
    }
}
