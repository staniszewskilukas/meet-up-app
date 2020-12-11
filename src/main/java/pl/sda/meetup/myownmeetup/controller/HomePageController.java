package pl.sda.meetup.myownmeetup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.meetup.myownmeetup.converters.EventModelToEventDto;
import pl.sda.meetup.myownmeetup.service.EventServiceImpl;
import pl.sda.meetup.myownmeetup.service.UserServiceImpl;

@Controller
@Slf4j
public class HomePageController {

    private final EventServiceImpl eventService;
    private final UserServiceImpl userService;
    private final EventModelToEventDto eventConverter;

    public HomePageController(EventServiceImpl eventService, UserServiceImpl userService, EventModelToEventDto eventConverter) {
        this.eventService = eventService;
        this.userService = userService;
        this.eventConverter = eventConverter;
    }

    @GetMapping({"/", "", "/homePage","/homePage.html"})
    public String getIndex(Model model) {
        model.addAttribute("list",
                       eventService.sortsListOfEventsModels(
                                eventService.listOfEventsDatesValidation(
                                        eventService.findAllEvents())).stream().map(eventConverter::convert));
        model.addAttribute("userEmail", userService.getLoggedUserName());
        return "homePage";
    }

    @GetMapping("/restricted")
    public String restrictedPage() {
        return "restricted";
    }
}

