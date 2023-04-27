package com.example.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String accno;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String accbal;

  @Column(nullable = false)
  private String pin;

  public Inventory() {
  }

  public Inventory(String accno, String username, String accbal, String pin) {
    this.accno = accno;
    this.username = username;
    this.accbal = accbal;
    this.pin = pin;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAccno() {
    return accno;
  }

  public void setAccno(String accno) {
    this.accno = accno;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAccbal() {
    return accbal;
  }

  public void setAccbal(String accbal) {
    this.accbal = accbal;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }
}