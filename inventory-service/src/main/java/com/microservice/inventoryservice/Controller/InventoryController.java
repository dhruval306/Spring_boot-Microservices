package com.microservice.inventoryservice.Controller;

import com.microservice.inventoryservice.Dto.InventoryResponse;
import com.microservice.inventoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isStock(skuCode);
    }
}
