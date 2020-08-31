package pl.sda.meetup.myownmeetup.service;

import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.dto.EventDto;

import java.util.List;

public interface EventService {

    void save(EventDto eventDto);

    List<EventModel> findEventsModelsByTitleRegardlessOfDate(String searchedTitle);

    EventDto findEventDtoById(Long id);

    EventModel findEventModelById(Long id);
}
