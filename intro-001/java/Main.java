/*
hello world with java-time example
 */

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;


public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World");
    Instant timestamp = Instant.now();
    System.out.println("Current Timestamp: " + timestamp);

    ZonedDateTime newYorkTime = timestamp.atZone(ZoneId.of("America/New_York"));
    System.out.println("Current Timestamp in New York: " + newYorkTime);

    ZonedDateTime londonTime = timestamp.atZone(ZoneId.of("Europe/London"));
    System.out.println("Current Timestamp in London: " + londonTime);
  }
}



