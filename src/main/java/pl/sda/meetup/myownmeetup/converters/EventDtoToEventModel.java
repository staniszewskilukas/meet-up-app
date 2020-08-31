package pl.sda.meetup.myownmeetup.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.dao.UserModel;
import pl.sda.meetup.myownmeetup.dto.EventDto;
import pl.sda.meetup.myownmeetup.service.UserServiceImpl;

@Component
public class EventDtoToEventModel implements Converter<EventDto, EventModel> {

    private final UserServiceImpl userService;

    public EventDtoToEventModel(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public EventModel convert(EventDto eventDto) {
        if (eventDto == null) {
            return null;
        }
        final EventModel eventModel = new EventModel();
        eventModel.setTitle(eventDto.getTitle());
        eventModel.setFrom(eventDto.getFrom());
        eventModel.setTo(eventDto.getTo());
        eventModel.setDescription(eventDto.getDescription());
        UserModel loggedUserModel = userService.getLoggedUserModel(userService.getLoggedUserName());
        loggedUserModel.addEventModel(eventModel);
        eventModel.setUserModel(loggedUserModel);
        return eventModel;
    }
}
