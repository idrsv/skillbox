package main.controller;

import main.model.Task;
import main.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;


@Controller
public class DefaultController {


    private TaskRepository taskRepository;

    @Autowired
    public DefaultController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Value("${someParameter}")
    private Integer someParameter;


    @GetMapping("/")
    public String index(Model model) {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> taskArrayList = new ArrayList<>();
        for (Task task : taskIterable) {
            taskArrayList.add(task);
        }
        model.addAttribute("tasks",taskArrayList);
        model.addAttribute("tasksCount",taskArrayList.size());
//        model.addAttribute("someParameter",someParameter);
        return "index";
    }
}
