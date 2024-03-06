package com.microservice.inventoryservice.Repository;

import com.microservice.inventoryservice.Dto.InventoryResponse;
import com.microservice.inventoryservice.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
