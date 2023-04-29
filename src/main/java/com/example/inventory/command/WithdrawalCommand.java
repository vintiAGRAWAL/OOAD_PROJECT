package com.example.inventory.command;

import com.example.inventory.model.Inventory;

public class WithdrawalCommand implements Command {

    private final Inventory inventory;
    private final long amount;
    // private final InventoryRepo inventoryRepo;

    public WithdrawalCommand(Inventory inventory, long amount) {
        this.inventory = inventory;
        this.amount = amount;
    }

    @Override
    public void execute() {
        long accbal = Long.parseLong(inventory.getAccbal());
        if (amount <= accbal) {
            inventory.setAccbal(String.valueOf(accbal - amount));
        }
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
