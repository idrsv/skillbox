import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        String hql = "FROM " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchaseList = session.createQuery(hql).getResultList();

        for (PurchaseList purchaseList1 : purchaseList){

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Course> courseCriteriaQuery = builder.createQuery(Course.class);
            Root<Course> courseRoot = courseCriteriaQuery.from(Course.class);
            courseCriteriaQuery.select(courseRoot).where(builder.equal(courseRoot.get("name"), purchaseList1.getCourseName()));
            Course course = session.createQuery(courseCriteriaQuery).getSingleResult();

            CriteriaQuery<Student> studentCriteriaQuery = builder.createQuery(Student.class);
            Root<Student> studentRoot = studentCriteriaQuery.from(Student.class);
            studentCriteriaQuery.select(studentRoot).where(builder.equal(studentRoot.get("name"), purchaseList1.getStudentName()));
            Student student = session.createQuery(studentCriteriaQuery).getSingleResult();

            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            linkedPurchaseList.setKey(new LinkedPurchaseList.LinkedPurchaseListKey(course.getId(),student.getId()));
            linkedPurchaseList.setCourseId(course.getId());
            linkedPurchaseList.setStudentId(student.getId());
            session.save(linkedPurchaseList);
        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
