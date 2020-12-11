package pl.sda.meetup.myownmeetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.meetup.myownmeetup.converters.UserDtoToUserModel;
import pl.sda.meetup.myownmeetup.dao.UserModel;
import pl.sda.meetup.myownmeetup.dto.UserDto;
import pl.sda.meetup.myownmeetup.service.UserServiceImpl;

import javax.validation.Valid;

@Controller
//@RequiredArgsConstructor on tworzy konstruktora tylko do pól oznaczonych jako final
public class RegisterController {

    private final UserServiceImpl userService;
    private final UserDtoToUserModel userConverter;

    public RegisterController(UserServiceImpl userService, UserDtoToUserModel userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping({"/registerForm", "/registerForm.html"})
    public String showRegister(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registerForm";
    }

    @PostMapping({"/registerForm", "/registerForm.html"})
    public String handleRegister(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registerForm";
        }
        UserModel userModel = userConverter.convert(userDto);
        userService.save(userModel);
        return "redirect:/event";
    }


}
