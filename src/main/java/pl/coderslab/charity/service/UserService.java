package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.dto.UserDto;
import pl.coderslab.charity.dto.UserLoginDto;
import pl.coderslab.charity.entity.MyUser;
import pl.coderslab.charity.entity.MyUserRoles;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.repository.UserRolesRepository;
import pl.coderslab.charity.util.UserAlreadyExistException;
import pl.coderslab.charity.util.UserIsNotRegisteredException;


@Transactional
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserRolesRepository userRolesRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public MyUser registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {

        if (emailExists(userDto.getEmail())){
            throw new UserAlreadyExistException("There is an account with that email: " + userDto.getEmail());
        }

        final MyUser newUser = new MyUser();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        MyUser myNewUser = userRepository.save(newUser);

        MyUserRoles newUserRole = new MyUserRoles();
        newUserRole.setUserId(myNewUser);
        newUserRole.setRole("USER");

        userRolesRepository.save(newUserRole);

        return myNewUser;

    }

    @Override
    public MyUser loginRegisteredUserAccount(UserLoginDto userLoginDto) throws UserIsNotRegisteredException {
        if (!userIsAlreadyRegistered(userLoginDto.getEmail(), userLoginDto.getPassword())){
            throw new UserIsNotRegisteredException("There is not a user registered with that email or password");
        }

        return userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
    }

    @Override
    public MyUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private boolean emailExists(String email){
        return userRepository.findByEmail(email) != null;
    }

    private boolean userIsAlreadyRegistered (String email, String password){
        return userRepository.findByEmailAndPassword(email,password) != null;
    }
}
