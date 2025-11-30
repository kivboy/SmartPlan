package by.vadarod.smartplan.entity;

import by.vadarod.smartplan.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "plans", name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "user_firstname", length=100)
    private String firstName;
    @Column (name = "user_lastname", length=100, nullable = false)
    private String lastName;
    @Column (name = "user_email", length=100, nullable = false)
    private String email;
    @Column (name = "user_role", nullable = false)
    private UserRole role;
    @Column (nullable = false)
    private String password;

}
