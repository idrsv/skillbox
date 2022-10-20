import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer salary;

    private Integer age;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    public List<Course> courses;
}
