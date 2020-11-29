package pl.sda.meetup.myownmeetup.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.sda.meetup.myownmeetup.converters.EventDtoToEventModel;
import pl.sda.meetup.myownmeetup.converters.EventModelToEventDto;
import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.date_and_time.DateValidator;
import pl.sda.meetup.myownmeetup.dto.EventDto;
import pl.sda.meetup.myownmeetup.repository.EventRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class EventServiceImplTest {

    private EventServiceImpl eventService;
    private EventRepository eventRepository = Mockito.mock(EventRepository.class);
    private EventDtoToEventModel eventDtoToEventModel = Mockito.mock(EventDtoToEventModel.class);
    private DateValidator dateValidator = Mockito.mock(DateValidator.class);
    private EventModelToEventDto eventModelToEventDto = Mockito.mock(EventModelToEventDto.class);

    @Before
    public void executeBeforeEach() {
        eventService = new EventServiceImpl(eventRepository, eventDtoToEventModel, dateValidator, eventModelToEventDto);
        EventDto eventDto = new EventDto();
        eventDto.setId(1L);
        eventDto.setTitle("Testowy Event 1");

        EventDto eventDto2 = new EventDto();
        eventDto2.setId(2L);
        eventDto2.setTitle("Testowy Event 2");
        eventService.save(eventDto);
        eventService.save(eventDto2);
    }

    @Test
    public void findAllEvents() {
        List<EventModel> allEvents = eventService.findAllEvents();

        assertEquals(2, allEvents.size());
        EventModel eventModel = allEvents.get(0);
        assertEquals(1, (long) eventModel.getId());
    }

    @Test
    public void listOfEventsDatesValidation() {
    }

    @Test
    public void sortsListOfEventsModels() {
    }

    @Test
    public void getListOfEventsDto() {
    }
}