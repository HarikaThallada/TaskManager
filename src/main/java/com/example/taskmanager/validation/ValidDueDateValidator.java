package com.example.taskmanager.validation;

import com.example.taskmanager.model.Task;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidDueDateValidator implements ConstraintValidator<ValidDueDate, Task> {
    @Override
    public boolean isValid(Task task, ConstraintValidatorContext context){
        if(Boolean.TRUE.equals(task.getCompleted())){
            return true;
        }

        LocalDate dueDate = task.getDueDate();
        if(dueDate == null){
            return false;
        }

        return !dueDate.isBefore(LocalDate.now());
    }
}
