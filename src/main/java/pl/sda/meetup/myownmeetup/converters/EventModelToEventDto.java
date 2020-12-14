package pl.sda.meetup.myownmeetup.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.date_and_time.TimeUtil;
import pl.sda.meetup.myownmeetup.dto.EventDto;

@Component
public class EventModelToEventDto implements Converter<EventModel, EventDto>{

    private final TimeUtil timeUtil;

    public EventModelToEventDto(TimeUtil timeUtil) {
        this.timeUtil = timeUtil;
    }

    @Override
    public EventDto convert(EventModel eventModel) {

        final EventDto eventDto = new EventDto();
        eventDto.setId(eventModel.getId());
        eventDto.setTitle(eventModel.getTitle());
        eventDto.setFrom(eventModel.getFrom());
        eventDto.setTo(eventModel.getTo());
        eventDto.setDescription(eventModel.getDescription());
        eventDto.setEventInTime(timeUtil.printTimePeriod(eventModel.getFrom(),eventModel.getTo()));
        timeUtil.showTimeDifference(eventModel.getFrom(),eventModel.getTo());
        eventDto.setTimeToEnd(timeUtil.printTimeToEnd());
        return eventDto;
    }
}
