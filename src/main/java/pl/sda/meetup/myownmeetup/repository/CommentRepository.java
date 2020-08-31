package pl.sda.meetup.myownmeetup.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.meetup.myownmeetup.dao.CommentModel;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<CommentModel, Long> {

    @Override
    Optional<CommentModel> findById(Long id);

    @Override
    List<CommentModel> findAll();

    List<CommentModel> findAllById(Long id);
}
