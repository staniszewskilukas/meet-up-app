package pl.sda.meetup.myownmeetup.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.meetup.myownmeetup.dao.RoleModel;

public interface RoleRepository extends CrudRepository<RoleModel, Long> {

    RoleModel findByRoleName(String roleName);

}
