package com.g1elproyectegrande.entity.auth;

//import jakarta.persistence.*;
import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        users.add(user);
    }

}
