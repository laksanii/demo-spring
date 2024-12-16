package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Employee {

    @Id
    private Long id;
    private String fullName;
    private String mobileNumber;
    private String emailId;
    private String dateOfBirth;


}
