package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.MyUserRoles;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<MyUserRoles, Integer> {
    List<MyUserRoles> findAllByUserId_Email(String email);
}
