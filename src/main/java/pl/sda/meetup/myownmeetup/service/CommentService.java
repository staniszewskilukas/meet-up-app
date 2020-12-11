package pl.sda.meetup.myownmeetup.service;

import org.springframework.data.jpa.repository.Query;
import pl.sda.meetup.myownmeetup.dao.CommentModel;
import pl.sda.meetup.myownmeetup.dto.CommentDto;
import pl.sda.meetup.myownmeetup.repository.CommentRepository;

import java.util.List;

public interface CommentService {

    CommentModel save(CommentModel commentDto, Long eventId);

    CommentModel findCommentById(Long id);

    List<CommentModel> findAllComments();

    List<CommentModel> findAllCommentsByEventId(Long eventId);
}
