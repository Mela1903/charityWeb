package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CharityServiceImpl;

import java.util.List;


@Controller
public class HomeController {

    private final CharityServiceImpl service;

    @Autowired
    public HomeController(CharityServiceImpl service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        List<Institution> allInstitutions = service.getAllInstitutions();
        model.addAttribute("institutions", allInstitutions);
        return "index";
    }

    @RequestMapping("/form")
    public String formAction(Model model){
        return "form";
    }
}
