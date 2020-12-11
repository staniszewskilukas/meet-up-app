package pl.sda.meetup.myownmeetup.service;

import com.sun.javaws.JnlpxArgs;
import org.junit.Test;
import org.mockito.Mockito;
import pl.sda.meetup.myownmeetup.dao.CommentModel;
import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.dao.UserModel;
import pl.sda.meetup.myownmeetup.dto.CommentDto;
import pl.sda.meetup.myownmeetup.repository.CommentRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

public class CommentServiceImplTest {

    private CommentServiceImpl commentService = Mockito.mock(CommentServiceImpl.class);

    @Test
    public void save() {
        //given
        CommentModel comment = new CommentModel();
        comment.setId(100L);
        comment.setContent("Treść testowa");
        //when
        commentService.save(comment, 1L);
        //then
        Mockito.verify(commentService, times(1)).save(comment, 1L);
    }

    @Test
    public void findCommentById() {
    }

    @Test
    public void findAllCommentsByEventId() {
    }
}