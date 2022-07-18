package com.jpmc.theater;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CustomerTests {
  @Test
  void equals() {
    Customer customer = new Customer("John Doe", "unused-id");
    Customer customerCopy = new Customer("John Doe", "unused-id");
    assertEquals(customer, customer);
    assertEquals(customer, customerCopy);
  }

  @Test
  void notEqualsName() {
    Customer customer1 = new Customer("Jane Doe", "unused-id");
    Customer customer2 = new Customer("John Doe", "unused-id");
    assertNotEquals(customer1, customer2);
  }

  @Test
  void notEqualsId() {
    Customer customer1 = new Customer("Jane Doe", "123");
    Customer customer2 = new Customer("Jane Doe", "12");
    assertNotEquals(customer1, customer2);
  }
}
