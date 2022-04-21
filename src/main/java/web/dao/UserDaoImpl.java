package web.dao;

import net.bytebuddy.dynamic.DynamicType;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;
import web.util.Gender;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addUser(User user) {
        sessionFactory.getCurrentSession().persist(user);
        return true;
    }

    @Override
    public List<User> getUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User", User.class).getResultList();
    }

    @Override
    public boolean updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        User oldUser = session.find( User.class, user.getId() );

        if ( null!=oldUser ) {
            session.merge(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.find( User.class, id );

        if (null!=user) {
            session.remove(user);
            return true;
        }
        return false;
    }
}
