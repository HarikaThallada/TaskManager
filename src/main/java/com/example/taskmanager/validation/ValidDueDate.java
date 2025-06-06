package com.example.taskmanager.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidDueDateValidator.class)
@Target({ElementType.TYPE}) // we validate entire task object
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDueDate {
    String message() default "Due Date must be today or in the future if the task is not completed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
