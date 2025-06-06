package com.example.taskmanager.model;

import com.example.taskmanager.validation.ValidDueDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidDueDate
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Title is empty" )
    private String title;

    private String description;

    @NotNull(message = "Due Date is required")
    private LocalDate dueDate;
    private Boolean completed;

}
