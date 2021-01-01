package pl.coderslab.charity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
    @Size(min = 1, message = "{Size.AdminLoginDto.email}")
    private String email;
    @Size(min = 1, message = "{Size.AdminLoginDto.password}")
    private String password;

}
