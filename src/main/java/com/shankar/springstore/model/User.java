package com.shankar.springstore.model;

import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.websocket.OnMessage;
import lombok.*;

@Entity
@Table(name="users") //"user" is reserved in PostgreSQL, so use "users"
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //auto-increment
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min=2, max=50)
    @Column(nullable = false)
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Column(nullable=false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;




}
