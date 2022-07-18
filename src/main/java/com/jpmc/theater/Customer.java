package com.jpmc.theater;

import java.util.Objects;

public class Customer {

  private String name;
  private String id;

  /**
   * @param name customer name
   * @param id customer id
   */
  public Customer(String name, String id) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Customer))
      return false;
    Customer customer = (Customer) o;
    return Objects.equals(this.name, customer.name) && Objects.equals(this.id, customer.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.id);
  }

  @Override
  public String toString() {
    return "name: " + this.name;
  }
}
