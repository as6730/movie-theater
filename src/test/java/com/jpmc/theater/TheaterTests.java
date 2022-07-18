package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests {
  @Test
  void totalFeeForCustomer() {
    Theater theater = new Theater(LocalDateProvider.singleton());
    Customer john = new Customer("John Doe", "id-12345");
    Reservation reservation = theater.reserve(john, 2, 4);

    assertEquals(reservation.totalFee(), 50);
  }

  @Test
  void throwsErrorWhenNoShowing() {
    Theater theater = new Theater(LocalDateProvider.singleton());
    Customer john = new Customer("John Doe", "id-12345");

    assertThrows(
      IllegalStateException.class,
      () -> { theater.reserve(john, 12, 4); },
      "not able to find any showing for given sequence 12"
    );
  }

  @Test
  void printMovieSchedule() {
    Theater theater = new Theater(LocalDateProvider.singleton());
    theater.printSchedule();
  }
}
