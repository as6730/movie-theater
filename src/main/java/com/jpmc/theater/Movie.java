package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Movie {
  private static int MOVIE_CODE_SPECIAL = 1;
  private static int MOVIE_DAY = 7;
  private static int MOVIE_DAY_SPECIAL = 1;
  private static int AFTERNOON_SPECIAL_BEGINS = 11;
  private static int AFTERNOON_SPECIAL_ENDS = 16;
  private static int MOVIE_SEQUENCE_1 = 1;
  private static int MOVIE_SEQUENCE_1_SPECIAL = 3;
  private static int MOVIE_SEQUENCE_2 = 2;
  private static int MOVIE_SEQUENCE_2_SPECIAL = 2;
  private static double TWENTY_FIVE_PERCENT_DISCOUNT = 0.25;
  private static double TWENTY_PERCENT_DISCOUNT = 0.2;

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
    return this.ticketPrice - getDiscount(showing.getSequenceOfTheDay(), showing.getStartTime());
  }

  private double getSpecialDiscounts(double totalDiscount, LocalDateTime showStartTime) {
    var endsOnTheHour = showStartTime.getHour() == AFTERNOON_SPECIAL_ENDS && showStartTime.getMinute() == 0;
    var afterSpecialBeginsAndBeforeSpecialEnds = showStartTime.getHour() >= AFTERNOON_SPECIAL_BEGINS && showStartTime.getHour() < AFTERNOON_SPECIAL_ENDS;

    if (afterSpecialBeginsAndBeforeSpecialEnds || endsOnTheHour) {
      totalDiscount = Math.max(totalDiscount, this.ticketPrice * TWENTY_FIVE_PERCENT_DISCOUNT); // 25% discount for special movie
    } else if (MOVIE_CODE_SPECIAL == specialCode) {
      totalDiscount = Math.max(totalDiscount, this.ticketPrice * TWENTY_PERCENT_DISCOUNT); // 20% discount for special movie
    }

    return totalDiscount;
  }

  private double getSequenceDiscounts(double totalDiscount, int showSequence) {
    if (showSequence == MOVIE_SEQUENCE_1) {
      totalDiscount = Math.max(totalDiscount, MOVIE_SEQUENCE_1_SPECIAL); // $3 discount for 1st show
    } else if (showSequence == MOVIE_SEQUENCE_2) {
      totalDiscount = Math.max(totalDiscount, MOVIE_SEQUENCE_2_SPECIAL); // $2 discount for 2nd show
    }

    return totalDiscount;
  }

  private double getDiscount(int showSequence, LocalDateTime showStartTime) {
    double totalDiscount = 0;

    totalDiscount = Math.max(getSpecialDiscounts(totalDiscount, showStartTime), getSequenceDiscounts(totalDiscount, showSequence));

    if (showStartTime.getDayOfMonth() == MOVIE_DAY) {
      totalDiscount = Math.max(totalDiscount, MOVIE_DAY_SPECIAL); // 1$ discount for movies showing on the 7th
    }

    return totalDiscount;
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
