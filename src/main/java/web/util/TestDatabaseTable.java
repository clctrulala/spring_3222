package web.util;

import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TestDatabaseTable {

    static public void fillDataset(JpaTransactionManager transactionManager) {
        EntityManager entityManager = transactionManager.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        String sql = "insert into User(name, birthdate, gender, phone) values ('Jhon Connor', str_to_date('1984-01-01', '%Y-%m-%d'), 0, '+75551122334'),('Sara Connor', str_to_date('1967-06-05', '%Y-%m-%d'), 1, '+70005566778');";
        transaction.begin();
        entityManager.createNativeQuery(sql).executeUpdate();
        transaction.commit();
        entityManager.close();
    }
}
