package pl.sda.meetup.myownmeetup.service;

import pl.sda.meetup.myownmeetup.dao.CommentModel;
import pl.sda.meetup.myownmeetup.dto.CommentDto;
import pl.sda.meetup.myownmeetup.repository.CommentRepository;

import java.util.List;

public interface CommentService {

    void save(CommentDto commentDto, Long eventId);

    CommentDto findCommentById(Long id);

    List<CommentDto> findAllComments();

    List<CommentDto> findAllCommentsByEventId(Long eventId);
}
