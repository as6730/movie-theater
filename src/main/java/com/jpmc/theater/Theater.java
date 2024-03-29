package com.jpmc.theater;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Theater {

  private List<Showing> schedule;

  public Theater() {
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
    Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
    Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
    LocalDate now = LocalDate.now();

    this.schedule = List.of(
      new Showing(turningRed, 1, LocalDateTime.of(now, LocalTime.of(9, 0))),
      new Showing(spiderMan, 2, LocalDateTime.of(now, LocalTime.of(11, 0))),
      new Showing(theBatMan, 3, LocalDateTime.of(now, LocalTime.of(12, 50))),
      new Showing(turningRed, 4, LocalDateTime.of(now, LocalTime.of(14, 30))),
      new Showing(spiderMan, 5, LocalDateTime.of(now, LocalTime.of(16, 10))),
      new Showing(theBatMan, 6, LocalDateTime.of(now, LocalTime.of(17, 50))),
      new Showing(turningRed, 7, LocalDateTime.of(now, LocalTime.of(19, 30))),
      new Showing(spiderMan, 8, LocalDateTime.of(now, LocalTime.of(21, 10))),
      new Showing(theBatMan, 9, LocalDateTime.of(now, LocalTime.of(23, 0)))
    );
  }

  public Reservation reserve(Customer customer, int sequence, int numTickets) {
    Showing showing;

    try {
      showing = this.schedule.get(sequence - 1);
    } catch (RuntimeException ex) {
      ex.printStackTrace();
      throw new IllegalStateException(
          "not able to find any showing for given sequence " + sequence);
    }

    return new Reservation(customer, showing, numTickets);
  }

  public void printSchedule() {
    System.out.println(LocalDate.now());
    System.out.println("===================================================");
    this.schedule.forEach(s -> System.out
        .println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle()
            + " " + TheaterUtils.humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.calculateCharge(1)));
    System.out.println("===================================================");
  }

  public void printScheduleJson() {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Showing.class, new ShowingAdapter());
    builder.setPrettyPrinting();

    Gson gson = builder.create();

    System.out.println(gson.toJson(this));  
  }

  public static void main(String[] args) {
    Theater theater = new Theater();
    theater.printSchedule();
    theater.printScheduleJson();
  }
}
