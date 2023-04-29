// package com.example.inventory.controller;

// import com.example.inventory.model.Inventory;
// import com.example.inventory.repository.InventoryRepo;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// @Controller
// @RequestMapping("/atm")
// public class InventoryController {

//   @Autowired
//   private InventoryRepo inventoryRepo;

//   @GetMapping("/home")
//   public String showHomePage() {
//     return "home";
//   }

//   @GetMapping("/addInventory")
//   public String showAddBookForm(Model model) {
//     model.addAttribute("inventory", getInventory());
//     return "addInventory";
//   }

//   @PostMapping("/add")
//   public String addInventory(Inventory inventory) {
//     inventoryRepo.save(inventory);
//     return "redirect:/atm/list";
//   }

//   @GetMapping("/list")
//   public String listInventory(Model model) {
//     model.addAttribute("inventory", inventoryRepo.findAll());
//     return "listInventory";
//   }

//   @GetMapping("/withdrawl")
//   public String showWithdrawlPage() {
//     return "withdrawl";
//   }

//   @GetMapping("/transfer")
//   public String showTransferPage() {
//     return "transfer";
//   }

//   @GetMapping("/changepin")
//   public String showChangePINPage() {
//     return "changepin";
//   }

//   @GetMapping("/checkbalance")
//   public String showCheckBalancePage() {
//     return "checkbalance";
//   }

//   @GetMapping("/generatepin")
//   public String showGeneratePINpage() {
//     return "generatepin";
//   }

//   @GetMapping("/displayaccountinfo")
//   public String display() {
//     return "redirect:/atm/list";
//   }

//   @PostMapping("/withdrawl")
//   public String handleWithdrawl(@RequestParam("amount") Long amount, Model model) {
//     Inventory inventory = getInventory();
//     if (amount <= Integer.parseInt(inventory.getAccbal())) {
//       inventory.setAccbal(String.valueOf(Integer.parseInt(inventory.getAccbal()) - amount));
//       inventoryRepo.save(inventory);
//     }
//     return "redirect:/atm/list";
//   }

//   private Inventory getInventory() {
//     return inventoryRepo.findAll().get(0);
//   }
// }
/*
 * The factory method is implemented in the private method getInventory(). This
 * method retrieves the inventory from the database and returns it.
 * In the other controller methods, the getInventory() method is used to
 * retrieve the inventory object before performing any operations on it.
 * This avoids having to retrieve the inventory object multiple times throughout
 * the code.
 */

// package com.example.inventory.controller;

// import com.example.inventory.model.Inventory;
// import com.example.inventory.repository.InventoryRepo;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Observer;

// @Controller
// @RequestMapping("/atm")
// public class InventoryController implements Subject {

// @Autowired
// private InventoryRepo inventoryRepo;

// private List<Observer> observers = new ArrayList<>();

// @GetMapping("/home")
// public String showHomePage() {
// return "home";
// }

// @GetMapping("/addInventory")
// public String showAddBookForm(Model model) {
// model.addAttribute("inventory", getInventory());
// return "addInventory";
// }

// @PostMapping("/add")
// public String addInventory(Inventory inventory) {
// inventoryRepo.save(inventory);
// notifyObservers();
// return "redirect:/atm/list";
// }

// @GetMapping("/list")
// public String listInventory(Model model) {
// model.addAttribute("inventory", inventoryRepo.findAll());
// return "listInventory";
// }

// @GetMapping("/withdrawl")
// public String showWithdrawlPage() {
// return "withdrawl";
// }

// @GetMapping("/transfer")
// public String showTransferPage() {
// return "transfer";
// }

// @GetMapping("/changepin")
// public String showChangePINPage() {
// return "changepin";
// }

// @GetMapping("/checkbalance")
// public String showCheckBalancePage() {
// return "checkbalance";
// }

// @GetMapping("/generatepin")
// public String showGeneratePINpage() {
// return "generatepin";
// }

// @GetMapping("/displayaccountinfo")
// public String display() {
// return "redirect:/atm/list";
// }

// @PostMapping("/withdrawl")
// public String handleWithdrawl(@RequestParam("amount") Long amount, Model
// model) {
// Inventory inventory = getInventory();
// if (amount <= Integer.parseInt(inventory.getAccbal())) {
// inventory.setAccbal(String.valueOf(Integer.parseInt(inventory.getAccbal()) -
// amount));
// inventoryRepo.save(inventory);
// }
// return "redirect:/atm/list";
// }

// private Inventory getInventory() {
// return inventoryRepo.findAll().get(0);
// }

// @Override
// public void registerObserver(Observer observer) {
// observers.add(observer);
// }

// @Override
// public void removeObserver(Observer observer) {
// observers.remove(observer);
// }

// @Override
// public void notifyObservers() {
// for (Observer observer : observers) {
// observer.update();
// }
// }
// }

/*
 * In this updated version, the InventoryController implements the Subject
 * interface, which defines the methods registerObserver, removeObserver, and
 * notifyObservers.
 * The registerObserver method adds an observer to the list of observers,
 * the removeObserver method removes an observer from the list of observers, and
 * the notifyObservers method calls the update method on each observer in the
 * list.
 * 
 * In the addInventory method, the inventoryRepo.save(inventory) statement saves
 * the new inventory to the database, and then the notifyObservers() method is
 * called to notify all observers that a new inventory has been added. This
 * allows any observers that are interested in changes to the inventory to be
 * notified
 * and take appropriate action.
 
 */
package com.example.inventory.controller;

import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

  @PostMapping("/withdrawl")
  public String handleWithdrawl(@RequestParam("amount") Long amount, @RequestParam("pin") String pin, Model model) {
    Inventory inventory = getInventory(pin);
    if (inventory != null && amount <= Integer.parseInt(inventory.getAccbal())) {
      inventory.setAccbal(String.valueOf(Integer.parseInt(inventory.getAccbal()) - amount));
      inventoryRepo.save(inventory);
    }
    return "redirect:/atm/list";
  }

  private Inventory getInventory(String pin) {
    // return inventoryRepo.findAll().get(0);
    return inventoryRepo.findByPin(pin);
  }
}
