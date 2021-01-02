package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Integer> {

    MyUser findByEmailAndPassword (String email, String password);
    //    boolean existsAdminByEmailAndPassword(String email, String password);
    MyUser findByEmail(String email);
    MyUser findById(int id);

}
