package pl.sda.meetup.myownmeetup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.date_and_time.DateValidator;
import pl.sda.meetup.myownmeetup.exception.NotFoundException;
import pl.sda.meetup.myownmeetup.repository.EventRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final DateValidator dateValidator;

    public EventServiceImpl(EventRepository eventRepository, DateValidator dateValidator) {
        this.eventRepository = eventRepository;
        this.dateValidator = dateValidator;
    }

    public EventModel save(EventModel event) {
        return eventRepository.save(event);
    }

    @Override
    public List<EventModel> findEventsModelsByTitleRegardlessOfDate(String searchedTitle) {
        return eventRepository.findByTitle(searchedTitle);
    }

    @Override
    public EventModel findEventById(Long id) {
        Optional<EventModel> eventModel = eventRepository.findById(id);
        return eventModel.orElseThrow(() -> new NotFoundException("Szukane wydarzenie nie zostało znalezione"));
    }

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
}
