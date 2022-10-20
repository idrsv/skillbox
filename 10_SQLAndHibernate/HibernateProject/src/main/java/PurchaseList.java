import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PurchaseList")
public class PurchaseList {
    @EmbeddedId
    private PurchaseListKey key;

    @Column(name = "student_name", updatable = false, insertable = false)
    private String studentName;

    @Column(name = "course_name", updatable = false, insertable = false)
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;

    @Embeddable
    @Data
    public static class PurchaseListKey implements Serializable {
        @Column(name = "student_name")
        private String studentName;

        @Column(name = "course_name")
        private String courseName;
    }
}
