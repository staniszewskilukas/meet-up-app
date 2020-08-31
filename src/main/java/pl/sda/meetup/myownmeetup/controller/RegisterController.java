package pl.sda.meetup.myownmeetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.meetup.myownmeetup.dto.UserDto;
import pl.sda.meetup.myownmeetup.service.UserServiceImpl;

import javax.validation.Valid;

@Controller
//@RequiredArgsConstructor on tworzy konstruktora tylko do pól oznaczonych jako final
public class RegisterController {

    private final UserServiceImpl userService;

    public RegisterController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping({"/registerForm", "/registerForm.html"})
    public String showRegister(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registerForm";
    }

    @PostMapping({"/registerForm", "/registerForm.html"})
    public String handleRegister(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult) {//bindingResult to koszyczek do którego wpadają dane walidacji
        if (bindingResult.hasErrors()) {
            return "registerForm";
        }//bindingResult.rejectValue();  //metoda do dokładania własnego błedu
        userService.save(userDto);
        // return "event";//jeśli tu zrobie beżpośrednio event to będę wyświetlał nową stronę
        // ale pod starym adresem rejestrowania czyli będe podwójnie wysyłał dane rejestracji na serwer
        return "redirect:/event";//tak jest dobrze
    }


}
