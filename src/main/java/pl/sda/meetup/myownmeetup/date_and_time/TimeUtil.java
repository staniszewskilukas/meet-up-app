package pl.sda.meetup.myownmeetup.date_and_time;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Component
public class TimeUtil implements TimeDifference{

    Map<String, Long> timeDiffMap = new HashMap<>();

    public String determineTime(LocalDate from, LocalDate to) {
        LocalDate now = LocalDate.now();
        if (now.isBefore(from)) {
            return "odbędzie sie";
        } else if (now.isBefore(to)||now.isEqual(to)) {
            return "trwa";
        }
        return "zakończył się";
    }

   @Override
    public Map<String, Long> showDifference(LocalDate from, LocalDate to) {
        long years = ChronoUnit.YEARS.between(from, to);
        long months = ChronoUnit.MONTHS.between(from, to);
        long days = ChronoUnit.DAYS.between(from, to);
        timeDiffMap.put("lat", years);
        timeDiffMap.put("miesiecy", months);
        timeDiffMap.put("dni", days);
        return timeDiffMap;
    }

    @Override
    public String printTimePeriod(LocalDate from, LocalDate to) {
        String eventTime = determineTime(from,to);
        return "Event " + eventTime;
    }

    @Override
    public String printTimeToEnd() {
        StringBuilder result = new StringBuilder("Do zakończenia eventu pozostało");
        for (Map.Entry<String, Long> entry : timeDiffMap.entrySet()) {
            if (entry.getValue() != 0) {
                result.append(" ")
                        .append(entry.getValue())
                        .append(" ")
                        .append(entry.getKey())
                        .append(",");
            }
        }
        return result.toString();
    }
}
