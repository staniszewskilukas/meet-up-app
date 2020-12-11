package pl.sda.meetup.myownmeetup.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.meetup.myownmeetup.converters.EventDtoToEventModel;
import pl.sda.meetup.myownmeetup.converters.EventModelToEventDto;
import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.date_and_time.DateValidator;
import pl.sda.meetup.myownmeetup.dto.CommentDto;
import pl.sda.meetup.myownmeetup.dto.EventDto;
import pl.sda.meetup.myownmeetup.repository.EventRepository;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//
//@RunWith(SpringRunner.class)
//@DataJpaTest
public class EventServiceImplTest {

//    @TestConfiguration
//    static class EventServiceImplTestContextConfiguration {
//
//        @Bean
//        public EventService eventService() {
//            return new EventServiceImpl();
//        }
//    }

//    @Autowired
//    EventRepository eventRepository;
//
//    @Autowired
//    EventDtoToEventModel eventDtoToEventModel;

    private EventServiceImpl eventService;
    //    private EventRepository eventRepository = Mockito.mock(EventRepository.class);
//    private EventDtoToEventModel eventDtoToEventModel = Mockito.mock(EventDtoToEventModel.class);
    private DateValidator dateValidator = Mockito.mock(DateValidator.class);
    private EventModelToEventDto eventModelToEventDto = Mockito.mock(EventModelToEventDto.class);


//    @Before
//    public void executeBeforeEach() {
//        eventService = new EventServiceImpl(eventRepository, eventDtoToEventModel, dateValidator, eventModelToEventDto);
//        EventDto eventDto = new EventDto();
//        eventDto.setId(1L);
//        eventDto.setTitle("Testowy Event 1");
//
//        EventDto eventDto2 = new EventDto();
//        eventDto2.setId(2L);
//        eventDto2.setTitle("Testowy Event 2");
//        eventService.save(eventDto);
//        eventService.save(eventDto2);
//    }

    private EventRepository eventRepository = Mockito.mock(EventRepository.class);

    @Test
    public void save() {
        //given
        EventModel eventModel = new EventModel();
        eventModel.setId(100L);
        eventModel.setTitle("Tytuł testowa");
        //when
        eventRepository.save(eventModel);
        when(eventRepository.save(eventModel)).thenReturn(eventModel);
        //then
        Mockito.verify(eventRepository, times(1)).save(eventModel);
        verifyNoMoreInteractions(eventRepository);
        assertEquals(100L, (long) eventModel.getId());
    }

//    @Test
//    public void savesEvent() {
//        EventDto eventDto = new EventDto();
//        eventDto.setId(15L);
//        EventModel convert = eventDtoToEventModel.convert(eventDto);
////        EventModel event = eventRepository.save(new EventModel());
//        EventModel save = eventRepository.save(convert);
//        assertNotNull(convert);
//        assertNotNull(save);
//        assertEquals(convert.getId(), save.getId());
//    }

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