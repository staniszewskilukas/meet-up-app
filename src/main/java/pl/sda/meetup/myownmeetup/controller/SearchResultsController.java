package pl.sda.meetup.myownmeetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.meetup.myownmeetup.dto.EventDto;
import pl.sda.meetup.myownmeetup.service.EventServiceImpl;
import pl.sda.meetup.myownmeetup.service.UserServiceImpl;

import java.util.List;

@Controller
public class SearchResultsController {

    private final EventServiceImpl eventService;
    private final UserServiceImpl userService;

    public SearchResultsController(EventServiceImpl eventService, UserServiceImpl userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping({"/searchResults","/searchResults.html"})
    public String showEventByTitle(@RequestParam(name = "title") String title, Model model) {
        List<EventDto> listOfEventsDto = eventService.getListOfEventsDto(
                eventService.sortsListOfEventsModels(
                        eventService.findEventsModelsByTitleRegardlessOfDate(title)));
        model.addAttribute("title",title);
        model.addAttribute("list",listOfEventsDto);
        model.addAttribute("userEmail", userService.getLoggedUserName());
        return "searchResults";
    }
}
