package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView homeAction(){
        ModelAndView homePage = new ModelAndView("index");
        List<Institution> allInstitutions = service.getAllInstitutions();
        homePage.addObject("institutions", allInstitutions);
        int sumAllOfBags = service.getSumOfAllBags();
        homePage.addObject("bagsSum", sumAllOfBags);
        int countAllDonations = service.countAllDonations();
        homePage.addObject("donationsCount",countAllDonations);
        return homePage;
    }

    @RequestMapping("/form")
    public String formAction(Model model){
        return "form";
    }
}
