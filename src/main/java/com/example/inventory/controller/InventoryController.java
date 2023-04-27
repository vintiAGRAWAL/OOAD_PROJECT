package com.example.inventory.controller;

import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/atm")
public class InventoryController {

  @Autowired
  private InventoryRepo inventoryRepo;

  @GetMapping("/home")
  public String showHomePage() {
    return "home";
  }

  @GetMapping("/addInventory")
  public String showAddBookForm(Model model) {
    model.addAttribute("inventory", new Inventory());
    return "addInventory";
  }

  @PostMapping("/add")
  public String addInventory(Inventory inventory) {
    inventoryRepo.save(inventory);
    return "redirect:/atm/list";
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

  @GetMapping("/checkbalance")
  public String showCheckBalancePage() {
    return "checkbalance";
  }

  @GetMapping("/generatepin")
  public String showGeneratePINpage() {
    return "generatepin";
  }

  @GetMapping("/displayaccountinfo")
  public String display() {
    // model.addAttribute("inventory", inventoryRepo.findAll());
    // return "displayaccountinfo";
    // List<Inventory> accountInfoList = inventoryRepo.findAll();
    // model.addAttribute("accountInfoList", inventoryRepo.findAll());
    return "redirect:/atm/list";
  }

  @PostMapping("/withdrawl")
  public String handleWithdrawl(@RequestParam("amount") Long amount, Model model) {
    // Fetch the inventory from the database (assuming there is only one inventory
    // entry)
    Inventory inventory = inventoryRepo.findAll().get(0);

    // Check if the withdrawal amount is valid (i.e., not exceeding the available
    // balance)

    if (amount <= Integer.parseInt(inventory.getAccbal())) {
      // Update the inventory balance by subtracting the withdrawal amount
      // model.addAttribute("message", "Withdrawal successful. Updated balance: $" +
      // inventory.getpin());
      inventory.setAccbal(String.valueOf(Integer.parseInt(inventory.getAccbal()) - amount));
      inventoryRepo.save(inventory);
      // model.addAttribute("message", "Withdrawal successful. Updated balance: $" +
      // inventory.getAccbal());
    } else {
      // model.addAttribute("message", "Withdrawal failed. Insufficient balance.");
    }

    return "redirect:/atm/list";
  }

  // @PostMapping("/changepin")
  // public String handlechangepin(@RequestParam("pin") Long pin, Model model) {
  // // Fetch the inventory from the database (assuming there is only one
  // inventory
  // // entry)
  // Inventory inventory = inventoryRepo.findAll().get(0);

  // // Check if the withdrawal amount is valid (i.e., not exceeding the available
  // // balance)
  // if (pin == inventory.getatmpin()) {
  // // Update the inventory balance by subtracting the withdrawal amount
  // inventory.setatmpin(pin);
  // inventoryRepo.save(inventory);
  // model.addAttribute("message", "Withdrawal successful. Updated balance: $" +
  // inventory.getaccbal());
  // } else {
  // model.addAttribute("message", "Withdrawal failed. Insufficient balance.");
  // }

  // return "displayaccountinfo";
  // }

}
