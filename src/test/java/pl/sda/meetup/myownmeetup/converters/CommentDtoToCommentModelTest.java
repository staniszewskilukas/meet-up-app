package pl.sda.meetup.myownmeetup.converters;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.sda.meetup.myownmeetup.dao.CommentModel;
import pl.sda.meetup.myownmeetup.dao.UserModel;
import pl.sda.meetup.myownmeetup.dto.CommentDto;
import pl.sda.meetup.myownmeetup.service.UserServiceImpl;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CommentDtoToCommentModelTest {

    private final UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);
    private final CommentDtoToCommentModel converter = new CommentDtoToCommentModel(userService);


    @Test
    public void Convert_RegularCommentDto_ReturnsCommentModel() {
        //given
        CommentDto expected = new CommentDto();
        expected.setId(1L);
        expected.setContent("Treść testowa");
        expected.setAddingData(LocalDate.now());
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setName("Lukas");
        //when
        when(userService.getLoggedUserModel(any(String.class))).thenReturn(userModel);
        CommentModel actual = converter.convert(expected);
        actual.setUserModel(userModel);

        //then
        assertEquals(expected.getContent(),actual.getContent());
        assertEquals(expected.getAddingData(),actual.getAddingData());
    }
}