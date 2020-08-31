package pl.sda.meetup.myownmeetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.meetup.myownmeetup.dao.RoleModel;


import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NotNull(message = "Pole nie może być puste")
public class UserDto {

    @Size(max = 50,message = "Nazwa może mieć maksymalnie 50 znaków")
    private String name;

    @Pattern(regexp = ".+@.+")
    private String email;

    @Size(min =3, max = 30, message
            = "Hasło musi mieć przynajmniej 8 znaków, ale nie więcej niż 30")//TODO zmienić ilość znaków w haśle
    private String password;
}
