package pl.sda.meetup.myownmeetup.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.meetup.myownmeetup.dao.RoleModel;
import pl.sda.meetup.myownmeetup.dao.UserModel;
import pl.sda.meetup.myownmeetup.dto.UserDto;
import pl.sda.meetup.myownmeetup.repository.RoleRepository;
import pl.sda.meetup.myownmeetup.service.RoleServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

public class UserDtoToUserModelTest {

    private PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
//    private RoleServiceImpl roleService = Mockito.mock(RoleServiceImpl.class);
    RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
    private UserDtoToUserModel userDtoToUserModel;

    @Before
    public void beforeEachTest() {
        RoleServiceImpl roleService = new RoleServiceImpl(roleRepository);
        RoleModel defaultRole = roleService.findDefaultRole();
        userDtoToUserModel = new UserDtoToUserModel(passwordEncoder, roleService);
    }

    @Test
    public void shouldConvertDtoToModel() {
        //given
        UserDto userDto = new UserDto();
        userDto.setName("Test");
        userDto.setEmail("mail@mail");
        userDto.setPassword("atatyhj");
//        given(userDtoToUserModel.convert(userDto)).willReturn(new UserModel());

        //when
        UserModel userModel = userDtoToUserModel.convert(userDto);
        //then
//        Assert.assertEquals(userDto.getName(),userModel.getName());
    }
}