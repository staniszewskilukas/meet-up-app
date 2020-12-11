package pl.sda.meetup.myownmeetup.service;

import org.springframework.stereotype.Service;
import pl.sda.meetup.myownmeetup.converters.CommentDtoToCommentModel;
import pl.sda.meetup.myownmeetup.converters.CommentModelToCommentDto;
import pl.sda.meetup.myownmeetup.dao.CommentModel;
import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.dto.CommentDto;
import pl.sda.meetup.myownmeetup.exception.NotFoundException;
import pl.sda.meetup.myownmeetup.repository.CommentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final EventServiceImpl eventService;

    public CommentServiceImpl(CommentRepository commentRepository, EventServiceImpl eventService) {
        this.commentRepository = commentRepository;
        this.eventService = eventService;

    }

    @Override
    public CommentModel save(CommentModel comment, Long eventId) {
        EventModel eventModelById = eventService.findEventModelById(eventId);
        if (comment.getContent().length() > 500) {
            String content = comment.getContent();
            String substring = content.substring(0, 499);
            comment.setContent(substring);
            comment.setEventModel(eventModelById);
            return commentRepository.save(comment);
        }
        comment.setEventModel(eventModelById);
        return commentRepository.save(comment);
    }

    @Override
    public CommentModel findCommentById(Long id) {
        Optional<CommentModel> byId = commentRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Szukany komentarz nie został znaleziony"));
    }

    @Override
    public List<CommentModel> findAllComments() {
        List<CommentModel> allComments = commentRepository.findAll();
        return commentsSort(allComments);
    }

    private List<CommentModel> commentsSort(List<CommentModel> allComments) {
        Comparator<CommentModel> commentModelComparator = Comparator.comparing(CommentModel::getAddingData);
        allComments.sort(commentModelComparator);
        return allComments;
    }

    @Override
    public List<CommentModel> findAllCommentsByEventId(Long eventId) {
        EventModel event = eventService.findEventModelById(eventId);
        Set<CommentModel> commentModelsSet = event.getCommentModels();
        List<CommentModel> commentModelsList = new ArrayList<>(commentModelsSet);
        return commentsSort(commentModelsList);
    }
}
