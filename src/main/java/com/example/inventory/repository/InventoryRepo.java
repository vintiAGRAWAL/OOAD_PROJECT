package com.example.inventory.repository;

import com.example.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    Inventory findByPin(String pin);

    Inventory findByAccno(String accno);
}
