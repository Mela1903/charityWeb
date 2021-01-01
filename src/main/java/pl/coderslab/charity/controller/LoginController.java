package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.dto.UserLoginDto;
import pl.coderslab.charity.entity.MyUser;
import pl.coderslab.charity.service.IUserService;
import pl.coderslab.charity.util.UserIsNotRegisteredException;
import javax.validation.Valid;

@Controller
public class LoginController {

    private final IUserService userService;

    @Autowired
    public LoginController(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String loginForm(Model m){
//        m.addAttribute("adminLogin", new AdminLoginDto());
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView loginFormPost(@ModelAttribute("userLogin") @Valid UserLoginDto userLoginDto, BindingResult result){
        MyUser userLogged;

        if (result.hasErrors()){
            return new ModelAndView("/login", "userLogin", userLoginDto);
        }

        try{
            userLogged = userService.loginRegisteredUserAccount(userLoginDto);
        }catch (UserIsNotRegisteredException e){
            ModelAndView mav = new ModelAndView("/login", "userLogin", userLoginDto);
            mav.addObject("message", "There is not a user registered with that email or password");
            return mav;

        }

        return new ModelAndView("index", "userLogged", userLogged);
    }

}