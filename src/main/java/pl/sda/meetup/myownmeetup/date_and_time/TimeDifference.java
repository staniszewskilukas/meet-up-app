package pl.sda.meetup.myownmeetup.date_and_time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public interface TimeDifference extends PrintTime {

    Map<String, Long> showTimeDifference(LocalDate from, LocalDate to);

}
