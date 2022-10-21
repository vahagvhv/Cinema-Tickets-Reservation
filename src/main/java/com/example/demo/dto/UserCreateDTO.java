package com.example.demo.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String passwordHash;
}
