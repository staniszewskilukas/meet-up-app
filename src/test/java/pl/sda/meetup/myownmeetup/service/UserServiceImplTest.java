package pl.sda.meetup.myownmeetup.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import pl.sda.meetup.myownmeetup.converters.UserDtoToUserModel;
import pl.sda.meetup.myownmeetup.dao.UserModel;
import pl.sda.meetup.myownmeetup.dto.UserDto;
import pl.sda.meetup.myownmeetup.repository.UserRepository;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserDtoToUserModel userDtoToUserModel = Mockito.mock(UserDtoToUserModel.class);
    private RoleServiceImpl roleService = Mockito.mock(RoleServiceImpl.class);
    private UserServiceImpl userService;

    @Before
    public void executeBeforeEach() {
        userService = new UserServiceImpl(userRepository, roleService);
    }

    @Test
    public void save() {

    }

    @Test
    public void getLoggedUserName() {
    }

    @Test
    public void isAuthenticated() {
    }

    @Test
    public void getLoggedUserModel() {
    }
}