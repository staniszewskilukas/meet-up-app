package pl.sda.meetup.myownmeetup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.meetup.myownmeetup.converters.EventDtoToEventModel;
import pl.sda.meetup.myownmeetup.converters.EventModelToEventDto;
import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.date_and_time.TimeUtil;
import pl.sda.meetup.myownmeetup.dto.EventDto;
import pl.sda.meetup.myownmeetup.exception.NotFoundException;
import pl.sda.meetup.myownmeetup.repository.EventRepository;
import pl.sda.meetup.myownmeetup.date_and_time.DateValidator;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventDtoToEventModel eventDtoToEventModel;
    private final DateValidator dateValidator;
    private final EventModelToEventDto eventModelToEventDto;

    public EventServiceImpl(EventRepository eventRepository, EventDtoToEventModel eventDtoToEventModel, DateValidator dateValidator, EventModelToEventDto eventModelToEventDto) {
        this.eventRepository = eventRepository;
        this.eventDtoToEventModel = eventDtoToEventModel;
        this.dateValidator = dateValidator;
        this.eventModelToEventDto = eventModelToEventDto;
    }

    @Override
    public void save(EventDto eventDto) {
        EventModel eventModel = eventDtoToEventModel.convert(eventDto);
        if(eventModel!=null){
            eventRepository.save(eventModel);
        }
    }

    @Override
    public List<EventModel> findEventsModelsByTitleRegardlessOfDate(String searchedTitle) {
        return eventRepository.findByTitle(searchedTitle);
    }

    @Override
    public EventDto findEventDtoById(Long id) {
        Optional<EventModel> eventModel = eventRepository.findById(id);
        return eventModel.map(eventModelToEventDto::convert)
                .orElseThrow(() -> new NotFoundException("Szukane wydarzenie nie zostało znalezione"));
    }

    @Override
    public EventModel findEventModelById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Szukane wydarzenie nie zostało znalezione"));
    }

    public List<EventModel> findAllEvents() {
        return eventRepository.findAll();
    }

    public List<EventModel> listOfEventsDatesValidation(List<EventModel> eventModels) {
        List<EventModel> activeEvents = new ArrayList<>();
        for (EventModel eventModel : eventModels) {
            LocalDate from = eventModel.getFrom();
            LocalDate to = eventModel.getTo();
            if (dateValidator.ifThisDateValid(from, to)) {
                activeEvents.add(eventModel);
            }
        }
        return activeEvents;
    }

    public List<EventModel> sortsListOfEventsModels(List<EventModel> eventModels) {
        Comparator<EventModel> eventModelComparator = Comparator.comparing(EventModel::getFrom);
        eventModels.sort(eventModelComparator);
        return eventModels;
    }

    public List<EventDto> getListOfEventsDto(List<EventModel> eventModels) {
        List<EventDto> eventDtosList = new ArrayList<>();
        for (EventModel eventModel : eventModels) {
            eventDtosList.add(eventModelToEventDto.convert(eventModel));
        }
        return eventDtosList;
    }
}
