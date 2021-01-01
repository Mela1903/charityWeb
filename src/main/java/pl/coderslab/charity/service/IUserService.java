package pl.coderslab.charity.service;


import pl.coderslab.charity.dto.UserDto;
import pl.coderslab.charity.dto.UserLoginDto;
import pl.coderslab.charity.entity.MyUser;
import pl.coderslab.charity.util.UserAlreadyExistException;
import pl.coderslab.charity.util.UserIsNotRegisteredException;

public interface IUserService {
    MyUser registerNewUserAccount(UserDto adminDto) throws UserAlreadyExistException;
    MyUser loginRegisteredUserAccount(UserLoginDto adminLoginDto) throws UserIsNotRegisteredException;

    MyUser findByEmail(String email);
}
