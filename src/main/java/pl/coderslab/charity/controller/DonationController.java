package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.dto.DonationDto;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CharityServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DonationController {

    private final CharityServiceImpl service;

    @Autowired
    public DonationController(CharityServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/donation/save")
    public ModelAndView saveDonation(){
        ModelAndView mav = new ModelAndView("form1");

//        Donation donation = new Donation();
        mav.addObject("donation", new DonationDto());

        List<Category> allCategories = service.getAllCategories();
        mav.addObject("categories",allCategories);

        List<Institution> allInstitutions = service.getAllInstitutions();
        mav.addObject("institutions", allInstitutions);
        return mav;
    }

    @PostMapping("/donation/save")
    public ModelAndView saveDonationPost(@ModelAttribute("saveDonation") @Valid DonationDto donationDto){

        service.saveDonation(donationDto);
        ModelAndView mav = new ModelAndView("form-confirmation");
        mav.addObject("saveDonation", donationDto);

        return mav;
    }

}
