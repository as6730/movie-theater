package com.jpmc.theater;

import java.util.concurrent.TimeUnit;
import java.time.Duration;

public class TheaterUtils {
  public static String humanReadableFormat(Duration duration) {
    long hour = duration.toHours();
    long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

    return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin,
        handlePlural(remainingMin));
  }

  // (s) postfix should be added to handle plural correctly
  private static String handlePlural(long value) {
    if (value == 1) {
      return "";
    } else {
      return "s";
    }
  }
}
