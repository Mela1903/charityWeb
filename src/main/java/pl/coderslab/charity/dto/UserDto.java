package pl.coderslab.charity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.charity.validator.PasswordMatches;
import pl.coderslab.charity.validator.ValidEmail;
import pl.coderslab.charity.validator.ValidPassword;

import javax.validation.constraints.Size;

@PasswordMatches
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Size(min = 1, message = "The first name cannot be empty")
    private String firstName;


    @Size(min = 1, message = "The last name cannot be empty")
    private String lastName;

    @ValidEmail
    @Size(min = 1, message = "The email cannot be empty")
    private String email;

    @ValidPassword
    private String password;

    @Size(min = 1)
    private String repassword;

}
