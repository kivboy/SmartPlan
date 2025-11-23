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

    public User(String firstName, String lastName, String email, UserRole role, String password) {
        if ((firstName != null) && firstName.isBlank()) {
            throw new IllegalArgumentException("User firstName must not be blank!");
        }
        if ((lastName == null) || lastName.isBlank()) {
            throw new IllegalArgumentException("User lastName must not be null or blank!");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("User email not correct or null!");
        }
        if (role == null) {
            throw new IllegalArgumentException("User role must not be null!");
        }
        if ((password == null) || password.isBlank()) {
            throw new IllegalArgumentException("User password must not be null or blank!");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(regex);
    }
}
