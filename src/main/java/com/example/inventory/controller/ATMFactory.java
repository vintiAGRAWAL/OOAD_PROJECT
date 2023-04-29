package com.example.inventory.controller;

import com.example.inventory.model.Inventory;

public interface ATMFactory {
    void generatePIN(Inventory inventory, String newPin);

    // void withdraw(Inventory inventory, int amount);

    void transfer(Inventory sourceInventory, Inventory destinationInventory, int amount);

    void changePIN(Inventory inventory, String currentPin, String newPin);

    void checkBalance(Inventory inventory);
}
