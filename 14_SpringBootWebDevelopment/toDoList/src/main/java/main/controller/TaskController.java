package main.controller;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;
import java.util.List;
import javax.persistence.Id;



@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> list() {
        List<Task> taskList = taskService.findAll();
        return taskList;
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity editById(@PathVariable int id, Task task) {
        return taskService.editById(id, task);
    }

    @PostMapping("/tasks")
    public Task add(@RequestBody Task task) {
        return taskService.save(task);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
        return taskService.findById(id);
    }

//    @DeleteMapping("/tasks/{id}")
//    public void deleted(@PathVariable int id) {
//        taskService.deletedById(id);
//    }

    @DeleteMapping("/tasks")
    public void deletedAll(){
        taskService.deletedAll();
    }
}


//{
//    "name" : "First task",
//    "description" : "test"
//}

/*
    1 - Создание дела
    2 - Удаление дела
    3 - Обновление дела
    4 - Получение списка дел
    5 - Удаление списка дел
 */