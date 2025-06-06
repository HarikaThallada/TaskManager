package com.example.taskmanager.service;

import com.example.taskmanager.exception.InvalidTaskException;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

   @Autowired
   private TaskRepository taskRepository;

   public List<Task> getAllTasks() {
        return taskRepository.findAll();
   }

   public Task getTaskById(Long id) {
       return taskRepository.findById(id).orElse(null);
   }

   public Task createTask(Task task){
       return taskRepository.save(task);
   }

    public Task updateTask(Long id, Task taskDetails) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        if (taskDetails.getTitle() == null || taskDetails.getTitle().trim().isEmpty()) {
            throw new InvalidTaskException("Title cannot be empty");
        }

        if (taskDetails.getDueDate() != null && taskDetails.getDueDate().isBefore(LocalDate.now())) {
            throw new InvalidTaskException("Due date cannot be in the past");
        }
        if (taskDetails.getTitle() != null) {
            existingTask.setTitle(taskDetails.getTitle());
        }

        if (taskDetails.getDescription() != null) {
            existingTask.setDescription(taskDetails.getDescription());
        }

        if (taskDetails.getDueDate() != null) {
            existingTask.setDueDate(taskDetails.getDueDate());
        }

        if (taskDetails.getCompleted() != null) {
            existingTask.setCompleted(taskDetails.getCompleted());
        }

        return taskRepository.save(existingTask);
    }

   public void deleteTaskById(Long id){
       taskRepository.deleteById(id);
   }

}