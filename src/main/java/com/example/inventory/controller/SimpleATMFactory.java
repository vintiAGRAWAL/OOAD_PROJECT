package com.example.inventory.controller;

import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepo;

public class SimpleATMFactory implements ATMFactory {
    private InventoryRepo inventoryRepo;

    @Override
    public void generatePIN(Inventory inventory, String newPin) {
        inventory.setPin(newPin);
        inventoryRepo.save(inventory);
    }

    // @Override
    // public void withdraw(Inventory inventory, int amount) {
    // if (amount <= Integer.parseInt(inventory.getAccbal())) {
    // inventory.setAccbal(String.valueOf(Integer.parseInt(inventory.getAccbal()) -
    // amount));
    // inventoryRepo.save(inventory);
    // }
    // }

    @Override
    public void transfer(Inventory sourceInventory, Inventory destinationInventory, int amount) {
        if (amount <= Integer.parseInt(sourceInventory.getAccbal())) {
            sourceInventory.setAccbal(String.valueOf(Integer.parseInt(sourceInventory.getAccbal()) - amount));
            destinationInventory.setAccbal(String.valueOf(Integer.parseInt(destinationInventory.getAccbal()) + amount));
            inventoryRepo.save(sourceInventory);
            inventoryRepo.save(destinationInventory);
        }
    }

    @Override
    public void changePIN(Inventory inventory, String currentPin, String newPin) {
        if (inventory.getPin().equals(currentPin)) {
            inventory.setPin(newPin);
            inventoryRepo.save(inventory);
        }
    }

    @Override
    public void checkBalance(Inventory inventory) {
        // Print out the account balance
    }
}
