package pl.sda.meetup.myownmeetup.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @ManyToMany(mappedBy = "roleModels")
    private Set<UserModel> userModels;

    public void addUserModel(UserModel userModel) {
        if (userModels == null) {
            userModels = new HashSet<>();
        }
        userModels.add(userModel);

    }

}
