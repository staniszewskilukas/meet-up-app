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
    public Map<String, Long> showTimeDifference(LocalDate from, LocalDate to) {
        long years = ChronoUnit.YEARS.between(from, to);
        long months = ChronoUnit.MONTHS.between(from, to);
        long days = ChronoUnit.DAYS.between(from, to);
       namesTimeDifferences(years, months, days);
       return timeDiffMap;
    }

    private void namesTimeDifferences(long years, long months, long days) {
        if(years==1){
            timeDiffMap.put("rok", years);
        }else {
            timeDiffMap.put("lat", years);
        }
        if(months==1){
            timeDiffMap.put("miesiąc", years);
        }else {
            timeDiffMap.put("miesiecy", months);
        }
        if(days==1){
            timeDiffMap.put("dzień", years);
        }else {
            timeDiffMap.put("dni", days);
        }
    }

    @Override
    public String printTimePeriod(LocalDate from, LocalDate to) {
        String eventTime = determineTime(from,to);
        return "Event " + eventTime;
    }

    @Override
    public String printTimeToEnd() {
        StringBuilder result = new StringBuilder("Do zakończenia eventu pozostało:");
        for (Map.Entry<String, Long> entry : timeDiffMap.entrySet()) {
            if (entry.getValue() != 0) {
                result.append(" ")
                        .append(entry.getValue())
                        .append(" ")
                        .append(entry.getKey());
            }
        }
        return result.toString();
    }
}
