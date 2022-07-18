package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

  @Test
  void totalFee() {
    var customer = new Customer("John Doe", "unused-id");
    var movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
    var showing = new Showing(movie, 1, LocalDateTime.now());

    assertTrue(new Reservation(customer, showing, 3).totalFee() == 37.5);
  }
}
