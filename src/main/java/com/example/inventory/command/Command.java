package com.example.inventory.command;

import com.example.inventory.model.Inventory;

public interface Command {

    Inventory getInventory();

    void execute();
}
