package by.vadarod.smartplan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(schema = "plans", name = "comments")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "comment_text", length=1000, nullable = false)
    private String text;
    @Column (nullable = false)
    private LocalDateTime created;
    private LocalDateTime updated;

}
