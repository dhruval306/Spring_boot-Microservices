package com.microservice.inventoryservice;

import com.microservice.inventoryservice.Model.Inventory;
import com.microservice.inventoryservice.Repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);

	}
//	@Bean
//	public CommandLineRunner lineRunner(InventoryRepository inventoryRepository){
//		return args -> {
//			Inventory inventory = new Inventory();
//			inventory.setSkuCode("T-Shirt");
//			inventory.setQuantity(300);
//			Inventory inventory1 = new Inventory();
//			inventory1.setSkuCode("I-Phone");
//			inventory1.setQuantity(0);
//			inventoryRepository.save(inventory);
//			inventoryRepository.save(inventory1);
//		};
//	}


}
