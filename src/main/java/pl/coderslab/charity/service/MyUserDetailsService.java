package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.MyUser;
import pl.coderslab.charity.entity.MyUserRoles;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.repository.UserRolesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            MyUser currentUser = userRepository.findByEmail(email);
        if (currentUser == null){
            throw new UsernameNotFoundException("User not found");
        }

        List<String> roles = new ArrayList<>();
        List<MyUserRoles> allByAdmin_email = userRolesRepository.findAllByUserId_Email(email);
        for (MyUserRoles a : allByAdmin_email){
            roles.add(a.getRole());
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roles != null){
            for (String role : roles){
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                grantList.add(authority);
            }
        }

        return new User(currentUser.getEmail(), currentUser.getPassword(), grantList);
    }
}

