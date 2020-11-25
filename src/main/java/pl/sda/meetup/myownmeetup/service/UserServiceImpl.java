package pl.sda.meetup.myownmeetup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.meetup.myownmeetup.converters.UserDtoToUserModel;
import pl.sda.meetup.myownmeetup.dao.UserModel;
import pl.sda.meetup.myownmeetup.dto.UserDto;
import pl.sda.meetup.myownmeetup.repository.UserRepository;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoToUserModel userDtoToUserModel;
    private final RoleServiceImpl roleService;

    public UserServiceImpl(UserRepository userRepository, UserDtoToUserModel userDtoToUserModel, RoleServiceImpl roleService) {
        this.userRepository = userRepository;
        this.userDtoToUserModel = userDtoToUserModel;
        this.roleService = roleService;
    }

    @Override
    public void save(UserDto userDto) {
        UserModel userModel = userDtoToUserModel.convert(userDto);
        userRepository.save(userModel);
    }

    public String getLoggedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return authentication.getName();
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated();
    }

    public UserModel getLoggedUserModel(String email){
        return userRepository.findByEmail(email);
    }


}
