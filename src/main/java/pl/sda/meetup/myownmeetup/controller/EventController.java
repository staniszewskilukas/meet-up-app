package pl.sda.meetup.myownmeetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.meetup.myownmeetup.dto.CommentDto;
import pl.sda.meetup.myownmeetup.dto.EventDto;
import pl.sda.meetup.myownmeetup.service.CommentServiceImpl;
import pl.sda.meetup.myownmeetup.service.EventServiceImpl;
import pl.sda.meetup.myownmeetup.service.UserServiceImpl;

import javax.validation.Valid;

@Controller
public class EventController {

    private final EventServiceImpl eventService;
    private final UserServiceImpl userService;
    private final CommentServiceImpl commentService;

    public EventController(EventServiceImpl eventService, UserServiceImpl userService, CommentServiceImpl commentService) {
        this.eventService = eventService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/event")
    public String addEvent(Model model) {
        EventDto eventDto = new EventDto();
        model.addAttribute("eventDto", eventDto);
        return "event";
    }

    @PostMapping("/event")
    public String handleEventAdding(@ModelAttribute @Valid EventDto eventDto,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Wal sie");//TODO
        }
        eventService.save(eventDto);
        return "redirect:/homePage";
    }

//    @GetMapping({"/searchBar/event/{eventDtoId}"})
//    public String showEventDetails(@PathVariable Long eventDtoId, Model model) {
//        model.addAttribute("eventDto", eventService.findEventDtoById(eventDtoId));
//        model.addAttribute("userEmail", userService.getLoggedUserName());
//        model.addAttribute("commentsList", commentService.findAllCommentsByEventId(eventDtoId));
//        return "event_details";
//    }

    @GetMapping({"/event_details/{eventDtoId}"})
    public String showEventDetails(@PathVariable Long eventDtoId, Model model) {
        model.addAttribute("eventDto", eventService.findEventDtoById(eventDtoId));
        model.addAttribute("userEmail", userService.getLoggedUserName());
        model.addAttribute("commentsList", commentService.findAllCommentsByEventId(eventDtoId));
        return "event_details";
    }

    @PostMapping({"/comment_add/{eventDtoId}"})
    public String addComment(@PathVariable Long eventDtoId, @ModelAttribute("commentDto") @Valid CommentDto commentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        commentService.save(commentDto, eventDtoId);
        return "redirect:/event_details";
    }
}
