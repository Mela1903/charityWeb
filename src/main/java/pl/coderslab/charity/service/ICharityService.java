package pl.coderslab.charity.service;

import org.apache.catalina.startup.Catalina;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;

import java.util.List;

@Service
@Transactional
public interface ICharityService {

    List<Institution> getAllInstitutions();

    int getSumOfAllBags();

    int countAllDonations();

    List<Category> getAllCategories();
}
