package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public boolean addUser(User user) {
        em.persist(user);
        return true;
    }

    @Override
    public List<User> getUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public boolean updateUser(User user) {
        if ( null!=em.find( User.class, user.getId() ) ) {
            em.merge(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(long id) {
        User user = em.find( User.class, id );

        if ( null!=user ) {
            em.remove(user);
            return true;
        }
        return false;
    }
}
