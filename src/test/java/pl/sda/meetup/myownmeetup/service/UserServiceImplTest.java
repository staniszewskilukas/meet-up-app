package pl.sda.meetup.myownmeetup.service;

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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserDtoToUserModel userDtoToUserModel = Mockito.mock(UserDtoToUserModel.class);
    private RoleServiceImpl roleService = Mockito.mock(RoleServiceImpl.class);
    private UserServiceImpl userService;

    @Before
    public void executeBeforeEach() {
        userService = new UserServiceImpl(userRepository, userDtoToUserModel, roleService);
    }

    @Test
    public void save() {
        UserDto userDto = new UserDto();
        userDto.setName("Testowy");
        userDto.setEmail("mail@mail.com");
        when(userRepository.save(any(UserModel.class))).then(returnsFirstArg());
        userService.save(userDto);
        UserModel foundUser = userRepository.findByEmail("mail@mail.com");
        assertNotNull(foundUser);
        assertEquals(foundUser.getEmail(), userDto.getEmail());
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