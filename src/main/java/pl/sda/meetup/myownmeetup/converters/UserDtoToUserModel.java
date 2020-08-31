package pl.sda.meetup.myownmeetup.converters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.meetup.myownmeetup.dao.RoleModel;
import pl.sda.meetup.myownmeetup.dao.UserModel;
import pl.sda.meetup.myownmeetup.dto.UserDto;
import pl.sda.meetup.myownmeetup.service.RoleServiceImpl;

@Component
@Slf4j
public class UserDtoToUserModel implements Converter<UserDto, UserModel> {

    private final PasswordEncoder passwordEncoder;
    private final RoleServiceImpl roleService;


    public UserDtoToUserModel(PasswordEncoder passwordEncoder, RoleServiceImpl roleService) {
        this.passwordEncoder = passwordEncoder;


        this.roleService = roleService;
    }

    @Override
    public UserModel convert(UserDto userDto) {
        if (userDto == null) {
            return null;
//TODO tu wypadałoby zmienić, spytać Jarka. Zwrócić jakiś błąd i komunikat by należało zapewne
        }
        final UserModel userModel = new UserModel();
        userModel.setName(userDto.getName());
        userModel.setEmail(userDto.getEmail());
        userModel.setPassword(passwordEncoder.encode(userDto.getPassword()));
        RoleModel roleModel = roleService.findDefaultRole();
        userModel.addRole(roleModel);
        return userModel;
    }
}
