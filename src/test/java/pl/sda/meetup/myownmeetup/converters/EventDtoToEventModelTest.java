package pl.sda.meetup.myownmeetup.converters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.dto.EventDto;
import pl.sda.meetup.myownmeetup.service.UserService;
import pl.sda.meetup.myownmeetup.service.UserServiceImpl;

import static org.junit.Assert.*;

public class EventDtoToEventModelTest {

    private EventDtoToEventModel eventDtoToEventModel;
    private UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);
    private EventDto eventDto;

    @Before
    public void executeBeforeEach(){
        eventDtoToEventModel = new EventDtoToEventModel(userService);
        eventDto = new EventDto();
        eventDto.setId(1L);
        eventDto.setTitle("EventDtoTitle");
    }

    @Test
    public void convert() {
        EventModel eventModel = eventDtoToEventModel.convert(eventDto);
        assertNotNull(eventModel);
        assertSame(eventModel.getClass(), EventModel.class);
    }
}