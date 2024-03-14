package com.microservice.inventoryservice.Service;

import com.microservice.inventoryservice.Dto.InventoryResponse;
import com.microservice.inventoryservice.Repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @SneakyThrows
    public List<InventoryResponse> isStock(List<String> skuCode) {
        log.info("wait start");
        Thread.sleep(10000);
        log.info("wait end");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder().skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
    }
}
