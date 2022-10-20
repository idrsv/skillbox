import entities.CourseType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('DESIGN','PROGRAMMING','MARKETING','MANAGEMENT','BUSINESS')")
    private CourseType type;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Teacher teacher;

    @Column(name = "students_count", nullable = true)
    private Integer studentsCount;

    private Integer price;

    @Column(name = "price_per_hour")
    public Float pricePerHour;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Subscriptions",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    public List<Student> students;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course_id")
    public List<Subscription> subscriptions;
}