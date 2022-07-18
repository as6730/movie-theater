package com.jpmc.theater;

import java.io.IOException;
import java.time.LocalDateTime;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

class ShowingAdapter extends TypeAdapter<Showing> {
  @Override
  public Showing read(JsonReader reader) throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void write(JsonWriter writer, Showing showing) throws IOException {
    writer.beginObject(); 
    writer.name("sequenceOfTheDay"); 
    writer.value(showing.getSequenceOfTheDay()); 
    writer.name("startTime"); 
    writer.value(showing.getStartTime().toString()); 
    writer.name("title"); 
    writer.value(showing.getMovie().getTitle()); 
    writer.name("runningTime"); 
    writer.value(TheaterUtils.humanReadableFormat(showing.getMovie().getRunningTime()));
    writer.name("charge"); 
    writer.value(showing.calculateCharge(1));
    writer.endObject();
  }
}

public class Showing {
  private Movie movie;
  private int sequenceOfTheDay;
  private LocalDateTime showStartTime;

  public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
    this.movie = movie;
    this.sequenceOfTheDay = sequenceOfTheDay;
    this.showStartTime = showStartTime;
  }

  public Movie getMovie() {
    return this.movie;
  }

  public LocalDateTime getStartTime() {
    return this.showStartTime;
  }

  public boolean isSequence(int sequence) {
    return this.sequenceOfTheDay == sequence;
  }

  public int getSequenceOfTheDay() {
    return this.sequenceOfTheDay;
  }

  public double calculateCharge(int numTickets) {
    return this.movie.calculateTicketPrice(this) * numTickets;
  }
}
