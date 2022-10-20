import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    @Column(name = "registration_date", columnDefinition = "DATE")
    private LocalDateTime registrationDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student_id")
    public List<Subscription> subscriptions;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
    public List<Course> courses;
}
