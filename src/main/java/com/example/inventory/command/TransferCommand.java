package com.example.inventory.command;

import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepo;
import com.example.inventory.service.ATMService;

public class TransferCommand implements Command {
    private final Inventory sourceInventory;
    private final Inventory destinationInventory;
    private final int amount;
    private final InventoryRepo inventoryRepo;
    private final ATMService atmService;

    public TransferCommand(Inventory sourceInventory, Inventory destinationInventory, int amount,
            InventoryRepo inventoryRepo, ATMService atmService) {
        this.sourceInventory = sourceInventory;
        this.destinationInventory = destinationInventory;
        this.amount = amount;
        this.inventoryRepo = inventoryRepo;
        this.atmService = atmService;
    }

    @Override
    public void execute() {
        atmService.transfer(sourceInventory, destinationInventory, amount);
        inventoryRepo.save(sourceInventory);
        inventoryRepo.save(destinationInventory);
    }

    @Override
    public Inventory getInventory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInventory'");
    }
}
