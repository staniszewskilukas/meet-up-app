package pl.sda.meetup.myownmeetup.date_and_time;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DateValidator {

    public boolean ifThisDateValid(LocalDateTime from, LocalDateTime to){

        LocalDateTime currentDate = LocalDateTime.now();

        if(from==null) {
            return false;
        } else if(currentDate.isAfter(to)){
            return false;
        }else if(from.isAfter(to)){
            return false;
        }
        return true;
    }
}
