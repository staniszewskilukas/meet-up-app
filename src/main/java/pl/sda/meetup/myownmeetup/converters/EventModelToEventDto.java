package pl.sda.meetup.myownmeetup.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.dto.EventDto;

@Component
public class EventModelToEventDto implements Converter<EventModel, EventDto>{

    @Override
    public EventDto convert(EventModel eventModel) {
        if(eventModel==null){
            return null;
        }
        final EventDto eventDto = new EventDto();
        eventDto.setId(eventModel.getId());
        eventDto.setTitle(eventModel.getTitle());
        eventDto.setFrom(eventModel.getFrom());
        eventDto.setTo(eventModel.getTo());
        eventDto.setDescription(eventModel.getDescription());
        return eventDto;
    }
}
