package ru.mpetrov.booksapi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Data
@NoArgsConstructor
public class Book {

    @NotNull
    @Id
    @GeneratedValue()
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String author;
}
