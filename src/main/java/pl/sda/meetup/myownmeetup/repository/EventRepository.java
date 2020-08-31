package pl.sda.meetup.myownmeetup.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.meetup.myownmeetup.dao.EventModel;

import java.util.List;
import java.util.Optional;


public interface EventRepository extends CrudRepository<EventModel, Long> {
    @Override
    List<EventModel> findAll();

    List<EventModel> findByTitle(String searchedTitle);

    @Override
    Optional<EventModel> findById(Long id);
}
