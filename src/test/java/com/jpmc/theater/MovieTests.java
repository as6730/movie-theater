package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
  Movie SPIDERMAN = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
  Movie TURNING_RED = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
  Movie THE_BATMAN = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);

  LocalDateTime TIME = LocalDateTime.of(2022, Month.JULY, 1, 19, 30, 40);

  @Test
  void noDiscountApplied() {
    Showing showing = new Showing(THE_BATMAN, 9, TIME);

    assertEquals(9, THE_BATMAN.calculateTicketPrice(showing));
  }

  @Test
  void specialMovieWith20PercentDiscount() {
    Showing showing = new Showing(SPIDERMAN, 5, TIME);

    assertEquals(10, SPIDERMAN.calculateTicketPrice(showing));
  }

  @Test
  void firstMovieOfDayWith3DollarDiscount() {
    Showing showing = new Showing(TURNING_RED, 1, TIME);

    assertEquals(8, TURNING_RED.calculateTicketPrice(showing));
  }

  @Test
  void secondMovieOfDayWith2DollarDiscount() {
    Showing showing = new Showing(THE_BATMAN, 2, TIME);

    assertEquals(7, THE_BATMAN.calculateTicketPrice(showing));
  }

  @Test
  void biggestDiscountWins() {
    Showing showing = new Showing(SPIDERMAN, 1, TIME);

    assertEquals(9.5, SPIDERMAN.calculateTicketPrice(showing));
  }
}
