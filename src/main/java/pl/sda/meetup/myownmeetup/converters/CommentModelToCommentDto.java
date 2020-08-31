package pl.sda.meetup.myownmeetup.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.sda.meetup.myownmeetup.dao.CommentModel;
import pl.sda.meetup.myownmeetup.dto.CommentDto;

@Component
public class CommentModelToCommentDto implements Converter<CommentModel, CommentDto> {

    @Override
    public CommentDto convert(CommentModel commentModel) {
        final CommentDto commentDto = new CommentDto();
        commentDto.setId(commentModel.getId());
        commentDto.setAddingData(commentModel.getAddingData());
        commentDto.setContent(commentModel.getContent());
        return commentDto;
    }
}
