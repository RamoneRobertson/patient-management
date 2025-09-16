package com.pm.patientservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Getter
@Setter
public class Patient {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotNull
  @Column(name = "name")
  private String name;

  @NotNull
  @Email
  @Column(unique = true)
  private String email;

  @NotNull
  @Column(name = "address")
  private String address;

  @NotNull
  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @NotNull
  @Column(name = "registered_date")
  private LocalDate registeredDate;

}
