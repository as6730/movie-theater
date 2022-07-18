package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {
  private static int MOVIE_CODE_SPECIAL = 1;

  private String title;
  private Duration runningTime;
  private double ticketPrice;
  private int specialCode;

  public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
    this.title = title;
    this.runningTime = runningTime;
    this.ticketPrice = ticketPrice;
    this.specialCode = specialCode;
  }

  public String getTitle() {
    return this.title;
  }

  public Duration getRunningTime() {
    return this.runningTime;
  }

  public double getTicketPrice() {
    return this.ticketPrice;
  }

  public double calculateTicketPrice(Showing showing) {
    return this.ticketPrice - getDiscount(showing.getSequenceOfTheDay());
  }

  private double getDiscount(int showSequence) {
    double specialDiscount = 0;
    if (MOVIE_CODE_SPECIAL == specialCode) {
      specialDiscount = this.ticketPrice * 0.2; // 20% discount for special movie
    }

    double sequenceDiscount = 0;
    if (showSequence == 1) {
      sequenceDiscount = 3; // $3 discount for 1st show
    } else if (showSequence == 2) {

      sequenceDiscount = 2; // $2 discount for 2nd show
    }
    // else {
    // throw new IllegalArgumentException("failed exception");
    // }

    // biggest discount wins
    return specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || this.getClass() != o.getClass())
      return false;

    Movie movie = (Movie) o;
    return Double.compare(this.ticketPrice, movie.ticketPrice) == 0
      && Objects.equals(this.title, movie.title)
      && Objects.equals(this.runningTime, movie.runningTime)
      && Objects.equals(this.specialCode, movie.specialCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.title, this.runningTime, this.ticketPrice, this.specialCode);
  }
}
