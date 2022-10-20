import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList implements Serializable {
    @EmbeddedId
    private LinkedPurchaseListKey key;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Integer courseId;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Integer studentId;

    @Embeddable
    @Data
    public static class LinkedPurchaseListKey implements Serializable {
        @ManyToOne
        @JoinColumn(name = "course_id")
        private Integer courseId;

        @ManyToOne
        @JoinColumn(name = "student_id")
        private Integer studentId;

        public LinkedPurchaseListKey(Integer courseId, Integer studentId) {
            this.courseId = courseId;
            this.studentId = studentId;
        }
    }
}























