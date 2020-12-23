package pl.coderslab.charity.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

@Component
public class Fixtures {

    @Autowired
    private DonationRepository donationRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void initData(){
        System.out.println("Init data");
//        createDonations();
    }


    private void createDonations(){
        Donation donation = new Donation();
        donation.setBagsQuantity(2);

        donationRepository.save(donation);
    }
}



