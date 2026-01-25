package by.vadarod.smartplan.entity;

import by.vadarod.smartplan.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(schema = "plans", name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements UserDetails {
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
    @Column (name = "login", length=50, nullable = false)
    private String login;
    @Column (nullable = false)
    private String password;
    @Column (nullable = false)
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
