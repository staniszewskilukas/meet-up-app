package pl.sda.meetup.myownmeetup.service;

import org.springframework.stereotype.Service;
import pl.sda.meetup.myownmeetup.converters.CommentDtoToCommentModel;
import pl.sda.meetup.myownmeetup.converters.CommentModelToCommentDto;
import pl.sda.meetup.myownmeetup.dao.CommentModel;
import pl.sda.meetup.myownmeetup.dao.EventModel;
import pl.sda.meetup.myownmeetup.dto.CommentDto;
import pl.sda.meetup.myownmeetup.exception.NotFoundException;
import pl.sda.meetup.myownmeetup.repository.CommentRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentDtoToCommentModel commentDtoToCommentModel;
    private final CommentModelToCommentDto commentModelToCommentDto;
    private final EventServiceImpl eventService;

    public CommentServiceImpl(CommentRepository commentRepository, CommentDtoToCommentModel commentDtoToCommentModel, CommentModelToCommentDto commentModelToCommentDto, EventServiceImpl eventService) {
        this.commentRepository = commentRepository;
        this.commentDtoToCommentModel = commentDtoToCommentModel;
        this.commentModelToCommentDto = commentModelToCommentDto;
        this.eventService = eventService;
    }

    @Override
    public void save(CommentDto commentDto, Long eventId) {
        EventModel eventModelById = eventService.findEventModelById(eventId);
        if (commentDto.getContent().length() > 500) {
            String content = commentDto.getContent();
            String substring = content.substring(0, 499);
            commentDto.setContent(substring);
            CommentModel commentModel = commentDtoToCommentModel.convert(commentDto);
            commentModel.setEventModel(eventModelById);
            commentRepository.save(commentModel);
        }
        CommentModel convert = commentDtoToCommentModel.convert(commentDto);
        convert.setEventModel(eventModelById);
        commentRepository.save(convert);


    }

    @Override
    public CommentDto findCommentById(Long id) {
        Optional<CommentModel> byId = commentRepository.findById(id);
        return byId.map(commentModelToCommentDto::convert)
                .orElseThrow(() -> new NotFoundException("Szukany komentarz nie został znaleziony"));
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<CommentModel> allComments = commentRepository.findAll();
        commentsSort(allComments);
        return allComments.stream().map(commentModelToCommentDto::convert).collect(Collectors.toList());

    }

    private void commentsSort(List<CommentModel> allComments) {
        Comparator<CommentModel> commentModelComparator = Comparator.comparing(CommentModel::getAddingData);
        allComments.sort(commentModelComparator);
    }

    @Override
    public List<CommentDto> findAllCommentsByEventId(Long eventId) {
        List<CommentModel> allById = commentRepository.findAllById(eventId);
        commentsSort(allById);
        return allById.stream().map(commentModelToCommentDto::convert).collect(Collectors.toList());
    }
}
