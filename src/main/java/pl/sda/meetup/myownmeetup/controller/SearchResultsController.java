package pl.sda.meetup.myownmeetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.meetup.myownmeetup.converters.EventModelToEventDto;
import pl.sda.meetup.myownmeetup.dto.EventDto;
import pl.sda.meetup.myownmeetup.service.EventServiceImpl;
import pl.sda.meetup.myownmeetup.service.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class SearchResultsController {

    private final EventServiceImpl eventService;
    private final UserServiceImpl userService;
    private final EventModelToEventDto converter;

    public SearchResultsController(EventServiceImpl eventService, UserServiceImpl userService, EventModelToEventDto converter) {
        this.eventService = eventService;
        this.userService = userService;
        this.converter = converter;
    }

    @GetMapping({"/searchResults","/searchResults.html"})
    public String showEventByTitle(@RequestParam(name = "title") String title, Model model) {
        List<EventDto> eventDtoList = eventService.sortsListOfEventsModels(
                eventService.findEventsModelsByTitleRegardlessOfDate(title)).stream().map(converter::convert).collect(Collectors.toList());
        model.addAttribute("title",title);
        model.addAttribute("list",eventDtoList);
        model.addAttribute("userEmail", userService.getLoggedUserName());
        return "searchResults";
    }
}
