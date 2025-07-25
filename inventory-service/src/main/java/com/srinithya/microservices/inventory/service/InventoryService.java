package com.srinithya.microservices.inventory.service;

import com.srinithya.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository ;

    public boolean isInStock(String skuCode,Integer quantity){
        // find an inventory for a given skucode where quantity >=0
      return   inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);

    }
}
