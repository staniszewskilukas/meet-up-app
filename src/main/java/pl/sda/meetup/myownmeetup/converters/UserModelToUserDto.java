package pl.sda.meetup.myownmeetup.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.sda.meetup.myownmeetup.dao.UserModel;
import pl.sda.meetup.myownmeetup.dto.UserDto;

@Component
public class UserModelToUserDto implements Converter<UserModel, UserDto> {

    @Override
    public UserDto convert(UserModel source) {

        final UserDto userDto = new UserDto();
        userDto.setName(source.getName());
        userDto.setEmail(source.getEmail());
        userDto.setPassword(source.getPassword());
        return userDto;
    }
}
