package pl.sda.meetup.myownmeetup.date_and_time;

import java.time.LocalDate;
import java.util.Map;

public interface PrintTime {

    public String printTimePeriod(LocalDate from, LocalDate to);

    public String printTimeToEnd();
}
