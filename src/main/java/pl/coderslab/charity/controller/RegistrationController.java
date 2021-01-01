package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.dto.UserDto;
import pl.coderslab.charity.entity.MyUser;
import pl.coderslab.charity.service.IUserService;
import pl.coderslab.charity.util.UserAlreadyExistException;


import javax.validation.Valid;


@Controller
public class RegistrationController {

    private final IUserService userService;

    @Autowired
    public RegistrationController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm(Model m){
        m.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView registerFormPost(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result){
        MyUser userRegistered;

        if (result.hasErrors()){
            return new ModelAndView("register", "user", userDto);
        }

        try{
            userRegistered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException e){
            ModelAndView mav = new ModelAndView("/register", "user", userDto);
            mav.addObject("message", "An account for that username/email already exists");
            return mav;
        }
        return new ModelAndView("redirect:login", "userRegistered", userRegistered);
    }

}