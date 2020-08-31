package pl.sda.meetup.myownmeetup.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleModel> roleModels;

    @OneToMany
    private Set<EventModel> eventModels;

    @OneToMany
    private Set<CommentModel> commentModels;

    public void addRole(RoleModel roleModel) {
        if (roleModels == null) {
            roleModels = new HashSet<>();
        }
        roleModels.add(roleModel);
        roleModel.addUserModel(this);
    }

  public void addEventModel(EventModel eventModel){
        if(eventModels==null) {
            eventModels = new HashSet<>();
        }
        eventModels.add(eventModel);
  }
}
