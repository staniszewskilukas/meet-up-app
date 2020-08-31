package pl.sda.meetup.myownmeetup.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.sda.meetup.myownmeetup.dao.CommentModel;
import pl.sda.meetup.myownmeetup.dao.UserModel;
import pl.sda.meetup.myownmeetup.dto.CommentDto;
import pl.sda.meetup.myownmeetup.service.UserServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class CommentDtoToCommentModel implements Converter<CommentDto, CommentModel> {

    private final UserServiceImpl userService;

    public CommentDtoToCommentModel (UserServiceImpl userService){
        this.userService=userService;
    }

    @Override
    public CommentModel convert(CommentDto comment) {
        final CommentModel commentModel = new CommentModel();
        UserModel userModel = userService.getLoggedUserModel(userService.getLoggedUserName());
        commentModel.setUserModel(userModel);
        commentModel.setAddingData(LocalDateTime.now());
        commentModel.setContent(comment.getContent());
        return commentModel;
    }
}
