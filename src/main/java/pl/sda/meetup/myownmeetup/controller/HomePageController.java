package pl.sda.meetup.myownmeetup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.meetup.myownmeetup.dto.EventDto;
import pl.sda.meetup.myownmeetup.service.EventServiceImpl;
import pl.sda.meetup.myownmeetup.service.UserServiceImpl;

import java.util.List;

@Controller
@Slf4j
public class HomePageController {

    private final EventServiceImpl eventService;
    private final UserServiceImpl userService;

    public HomePageController(EventServiceImpl eventService, UserServiceImpl userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping({"/", "", "/homePage","/homePage.html"})
    public String getIndex(Model model) {
        model.addAttribute("list",
                eventService.getListOfEventsDto(
                        eventService.sortsListOfEventsModels(
                                eventService.listOfEventsDatesValidation(
                                        eventService.findAllEvents()))));
        model.addAttribute("userEmail", userService.getLoggedUserName());
        return "homePage";
    }

    @GetMapping("/restricted")
    public String restrictedPage() {
        return "restricted";
    }
}

