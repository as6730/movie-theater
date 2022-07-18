package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MovieTests {
  Movie SPIDERMAN = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
  Movie TURNING_RED = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
  Movie THE_BATMAN = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);

  LocalDateTime TIME = LocalDateTime.of(2022, Month.JULY, 1, 19, 30, 40);
  LocalDateTime AFTERNOON_DISCOUNT_ENDS_ON_HR = LocalDateTime.of(2022, Month.JULY, 1, 16, 00, 00);
  LocalDateTime SEVENTH_DAY_DISCOUNT_TIME = LocalDateTime.of(2022, Month.JULY, 7, 19, 30, 40);

  @Test
  void noDiscountApplied() {
    Showing showing = new Showing(THE_BATMAN, 9, TIME);

    assertEquals(9, THE_BATMAN.calculateTicketPrice(showing));
  }

  @Test
  void specialDayDiscount() {
    Showing showing = new Showing(THE_BATMAN, 9, SEVENTH_DAY_DISCOUNT_TIME);

    assertEquals(8, THE_BATMAN.calculateTicketPrice(showing));
  }

  @Test
  void movieWithAfternoonDiscount() {
    Showing showing = new Showing(TURNING_RED, 5, AFTERNOON_DISCOUNT_ENDS_ON_HR);

    assertEquals(8.25, TURNING_RED.calculateTicketPrice(showing));
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

  @Test
  void equals() {
    Movie spidermanCopy = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
    assertEquals(SPIDERMAN, SPIDERMAN);
    assertEquals(SPIDERMAN, spidermanCopy);
  }
  
  @Test
  void notEqualsTitle() {
    Movie movie1 = new Movie("movie1", Duration.ofMinutes(90), 1, 1);
    Movie movie2 = new Movie("movie2", Duration.ofMinutes(90), 1, 1);
    assertNotEquals(movie1, movie2);
  }

  @Test
  void notEqualsRunningTime() {
    Movie movie1 = new Movie("movie", Duration.ofMinutes(1), 1, 1);
    Movie movie2 = new Movie("movie", Duration.ofMinutes(2), 1, 1);
    assertNotEquals(movie1, movie2);
  }

  @Test
  void notEqualsTicketPrice() {
    Movie movie1 = new Movie("movie", Duration.ofMinutes(1), 1, 1);
    Movie movie2 = new Movie("movie", Duration.ofMinutes(1), 2, 1);
    assertNotEquals(movie1, movie2);
  }

  @Test
  void notEqualsTicketSpecialCode() {
    Movie movie1 = new Movie("movie", Duration.ofMinutes(1), 1, 1);
    Movie movie2 = new Movie("movie", Duration.ofMinutes(1), 1, 2);
    assertNotEquals(movie1, movie2);
  }
}
