package pl.sda.meetup.myownmeetup.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateValidator {

    public boolean ifThisDateValid(LocalDate from, LocalDate to){

        LocalDate currentDate = LocalDate.now();

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
