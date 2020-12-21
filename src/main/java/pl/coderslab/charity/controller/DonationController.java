package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CharityServiceImpl;

import java.util.List;

@Controller
public class DonationController {

    private final CharityServiceImpl service;

    @Autowired
    public DonationController(CharityServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/donation/save")
    public ModelAndView formAction(){
        ModelAndView mav = new ModelAndView("form");

        Donation donation = new Donation();
        mav.addObject("donation",donation);

        List<Category> allCategories = service.getAllCategories();
        mav.addObject("categories",allCategories);

        List<Institution> allInstitutions = service.getAllInstitutions();
        mav.addObject("institutions", allInstitutions);
        return mav;
    }

//    @RequestMapping(value = "/form", method = RequestMethod.POST)
//    public ModelAndView formActionPost(){
//        ModelAndView form = new ModelAndView("form");
//        List<Category> allCategories = service.getAllCategories();
//        form.addObject("categories",allCategories);
//        List<Institution> allInstitutions = service.getAllInstitutions();
//        form.addObject("institutions", allInstitutions);
//        return form;
//    }


}
