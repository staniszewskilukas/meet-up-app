package pl.sda.meetup.myownmeetup.converters;

import org.junit.Test;
import pl.sda.meetup.myownmeetup.dao.CommentModel;
import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.dao.RoleModel;
import pl.sda.meetup.myownmeetup.dao.UserModel;
import pl.sda.meetup.myownmeetup.dto.CommentDto;
import pl.sda.meetup.myownmeetup.dto.EventDto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CommentModelToCommentDtoTest {

    CommentModelToCommentDto commentModelToCommentDto = new CommentModelToCommentDto();

    @Test
    public void Convert_RegularCommentModel_ReturnsCommentDto() {
        //given
        CommentModel expected = new CommentModel();
        expected.setId(1L);
        expected.setContent("Komentarz testowy");
        expected.setAddingData(LocalDate.now());
        //when
        CommentDto actual = commentModelToCommentDto.convert(expected);
        //then
        assertEquals(expected.getContent(),actual.getContent());
        assertEquals(expected.getAddingData(),actual.getAddingData());
        assertEquals(expected.getId(),actual.getId());
    }
}