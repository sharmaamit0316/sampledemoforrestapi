package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data  // Lombok will automatically generate getters, setters, toString, etc.
@NoArgsConstructor  // Lombok generates a no-argument constructor
@AllArgsConstructor // Lombok generates a constructor with all arguments
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 3, message = "Title must have at least 3 characters")
    private String title;

    private String description;
}
