package tpaql.exercise2;

import java.util.List;
import java.util.Optional;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task saveTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null");
        }
        if (task.getName() == null || task.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Task name must not be empty");
        }
        return taskRepository.save(task);
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task toggleTaskCompleted(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
        Task task = optionalTask.get();
        task.setCompleted(!task.isCompleted());
        return taskRepository.update(task);
    }
}
