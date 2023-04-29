
package com.example.inventory.controller;

import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepo;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.inventory.command.Command;
import com.example.inventory.command.Invoker;
import com.example.inventory.command.WithdrawalCommand;

@Controller
@RequestMapping("/atm")

public class InventoryController {

  @Autowired
  private InventoryRepo inventoryRepo;

  private ATMFactory atmFactory = new SimpleATMFactory();
  private final Queue<Command> commandQueue = new LinkedList<>();

  // @PostMapping("/generatepin")
  // public String showGeneratePIN(@RequestParam("newpin") String newPin,
  // @RequestParam("accno") String accno,
  // Model model) {
  // Inventory inventory = inventoryRepo.findByAccno(accno);
  // atmFactory.generatePIN(inventory, newPin);
  // return "redirect:/atm/list";
  // }

  @PostMapping("/withdrawl")
  public String handleWithdrawl(@RequestParam("amount") long amount, @RequestParam("pin") String pin, Model model) {
    Inventory inventory = inventoryRepo.findByPin(pin);
    if (inventory != null) {
      Command command = new WithdrawalCommand(inventory, amount);
      // Invoker invoker = new Invoker();
      // invoker.setCommand(command);
      // invoker.executeCommand();
      commandQueue.add(command);

    }
    while (!commandQueue.isEmpty()) {
      Command command = commandQueue.poll();
      command.execute();
      inventoryRepo.save(command.getInventory());
    }
    return "redirect:/atm/list";
  }

  // @PostMapping("/withdrawl")
  // public String handleWithdrawl(@RequestParam("amount") int amount,
  // @RequestParam("pin") String pin, Model model) {
  // Inventory inventory = inventoryRepo.findByPin(pin);
  // atmFactory.withdraw(inventory, amount);
  // return "redirect:/atm/list";
  // }

  @PostMapping("/transfer")
  public String handleTransfer(@RequestParam("amount") int amount, @RequestParam("sourceAccno") String sourceAccno,
      @RequestParam("destAccno") String destAccno, Model model) {
    Inventory sourceInventory = inventoryRepo.findByAccno(sourceAccno);
    Inventory destinationInventory = inventoryRepo.findByAccno(destAccno);
    atmFactory.transfer(sourceInventory, destinationInventory, amount);
    return "redirect:/atm/list";
  }

  @PostMapping("/changepin")
  public String showChangePIN(@RequestParam("newpin") String newpin, @RequestParam("pin") String pin, Model model) {
    Inventory inventory = getInventory(pin);
    if (inventory != null) {
      inventory.setPin(newpin);
      inventoryRepo.save(inventory);
    }
    return "redirect:/atm/list";
  }

  @PostMapping("/add")
  public String addInventory(Inventory inventory) {
    inventoryRepo.save(inventory);
    return "redirect:/atm/list";
  }

  @PostMapping("/generatepin")
  public String showGeneratePIN(@RequestParam("newpin") String newpin, @RequestParam("accno") String accno,
      Model model) {
    // Inventory inventory = getInventory(pin);
    Inventory inventory = inventoryRepo.findByAccno(accno);

    // Inventory inventory = new Inventory();
    if (inventory != null) {
      inventory.setPin(newpin);
      inventoryRepo.save(inventory);

    }

    return "redirect:/atm/list";
  }

  @GetMapping("/home")
  public String showHomePage() {
    return "home";
  }

  @GetMapping("/addInventory")
  public String showAddBookForm(Model model) {
    model.addAttribute("inventory", new Inventory());
    return "addInventory";
  }

  @GetMapping("/list")
  public String listInventory(Model model) {
    model.addAttribute("inventory", inventoryRepo.findAll());
    return "listInventory";
  }

  @GetMapping("/withdrawl")
  public String showWithdrawlPage() {
    return "withdrawl";
  }

  @GetMapping("/transfer")
  public String showTransferPage() {
    return "transfer";
  }

  @GetMapping("/changepin")
  public String showChangePINPage() {
    return "changepin";
  }

  @GetMapping("/generatepin")
  public String showGeneratePINpage() {
    return "generatepin";
  }

  @GetMapping("/displayaccountinfo")
  public String display() {
    return "redirect:/atm/list";
  }

  private Inventory getInventory(String pin) {
    // return inventoryRepo.findAll().get(0);
    return inventoryRepo.findByPin(pin);
  }

  // Other methods
}
