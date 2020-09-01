package pl.sda.meetup.myownmeetup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class LoginController {

    PasswordEncoder passwordEncoder;


    @GetMapping({"/sign-in","/sign-in.html"})
    public String getSignIn() {
        return "sign-in";
    }

//    @PostMapping({"/sign-in","/sign-in.html"})
//    public String loginSuccess(Model model){
//model.addAllAttributes("login-process",)
//    }

//    @PostMapping({"/sign-in","/sign-in.html"})
//    public String afterUserCreation(Model model) {
//     model.addAllAttributes()
//        return "homePage";
//    }

    @RequestMapping(value = {"/sign-in","/sign-in.html"}, method = RequestMethod.POST)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "/homePage";
    }

    // Login form with error
    @RequestMapping("/error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "sign-in.html";
    }

}


