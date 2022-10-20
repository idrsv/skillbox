package main.service;

import main.model.Task;
import main.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.persistence.Id;


@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        Iterable<Task> taskIterable = taskRepository.findAll();
        return new ArrayList<>((Collection<? extends Task>) taskIterable);
    }

    public ResponseEntity findById(int id){
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(task.get(), HttpStatus.OK);
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public ResponseEntity editById(int id, Task newTask){
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            newTask.setId(id);
            taskRepository.save(newTask);
            return new ResponseEntity(task.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

//    public void deletedById(int id){
//        if (taskRepository.findById(id).isEmpty()){
//            throw new EntityNotFoundException("Task not found");
//        } else taskRepository.deleteById(id);
//    }

    public void deletedAll(){
        List<Task> taskList = StreamSupport.stream(taskRepository.findAll().spliterator(),false).collect(Collectors.toList());
        if (taskList.isEmpty()){
            throw new EntityNotFoundException("Task list is empty");
        }
        taskRepository.deleteAll();
    }
}

