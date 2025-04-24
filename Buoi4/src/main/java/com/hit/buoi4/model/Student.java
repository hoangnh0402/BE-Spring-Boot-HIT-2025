package com.hit.buoi4.model;

import com.hit.buoi4.constant.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private String email;


    @NotNull
    private String phone;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classEntity;
}

