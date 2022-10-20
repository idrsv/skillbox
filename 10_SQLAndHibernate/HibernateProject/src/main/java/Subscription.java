import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @EmbeddedId
    private Key id;

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn (name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "subscription_date", columnDefinition = "DATE")
    @NotNull
    private LocalDateTime subscriptionDate;

    @Embeddable
    @Data
    public static class Key implements Serializable {
        @ManyToOne
        @JoinColumn (name = "student_id")
        private Student studentId;

        @ManyToOne
        @JoinColumn(name = "course_id")
        private Course courseId;
    }
}