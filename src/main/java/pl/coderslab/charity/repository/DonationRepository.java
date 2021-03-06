package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {

    @Query("SELECT coalesce(SUM(d.bagsQuantity)) FROM Donation d")
    int sumOfAllBags();

    @Query("select count(d) from Donation d")
    int countAllDonations();

}
