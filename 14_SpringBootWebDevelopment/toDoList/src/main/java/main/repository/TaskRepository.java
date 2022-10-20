package main.repository;

import main.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.Id;


@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
}