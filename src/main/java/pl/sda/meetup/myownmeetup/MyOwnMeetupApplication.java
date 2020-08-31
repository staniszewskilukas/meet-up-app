package pl.sda.meetup.myownmeetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.meetup.myownmeetup.dao.RoleModel;
import pl.sda.meetup.myownmeetup.dto.EventDto;
import pl.sda.meetup.myownmeetup.dto.UserDto;
import pl.sda.meetup.myownmeetup.service.EventServiceImpl;
import pl.sda.meetup.myownmeetup.service.UserServiceImpl;

import java.time.LocalDate;

@SpringBootApplication
public class MyOwnMeetupApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyOwnMeetupApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
    }
}
