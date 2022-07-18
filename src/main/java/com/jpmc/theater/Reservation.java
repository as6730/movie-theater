package com.jpmc.theater;

public class Reservation {
  private Customer customer;
  private Showing showing;
  private int numTickets;

  public Reservation(Customer customer, Showing showing, int numTickets) {
    this.customer = customer;
    this.showing = showing;
    this.numTickets = numTickets;
  }

  public double totalFee() {
    return showing.getMovieFee() * numTickets;
  }
}
