package pl.sda.meetup.myownmeetup.date_and_time;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Component
public class TimeUtil implements TimeDifference {

    Map<String, Long> timeDiffMap = new HashMap<>();
    private LocalDateTime from;
    private LocalDateTime to;

    public TimeUtil(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    private String determineTime() {
        String result;
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(from)) {
            return result = "odbędzie sie";
        } else if (now.isBefore(to)) {
            return result = "odbywa się";
        }
        return result = "odbywał się";
    }


    @Override
    public Map<String, Long> showDifference() {
        long years = ChronoUnit.YEARS.between(from, to);
        long months = ChronoUnit.MONTHS.between(from, to);
        long days = ChronoUnit.DAYS.between(from, to);
        long hours = ChronoUnit.HOURS.between(from, to);
        long minutes = ChronoUnit.MINUTES.between(from, to);
        timeDiffMap.put("lat", years);
        timeDiffMap.put("miesiecy", months);
        timeDiffMap.put("dni", days);
        timeDiffMap.put("godzin", hours);
        timeDiffMap.put("minut", minutes);
        return timeDiffMap;
    }

    @Override
    public String printTimePeriod() {
        String eventTime = determineTime();
        StringBuilder result = new StringBuilder("Event " + eventTime);


        return null;
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
