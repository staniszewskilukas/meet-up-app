package pl.sda.meetup.myownmeetup.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.meetup.myownmeetup.dao.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Long> {
    UserModel findByEmail(String userName);
}
