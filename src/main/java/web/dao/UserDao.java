package web.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.model.User;
import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    @Override
    <T extends User> T save(T user);

    @Override
    List<User> findAll();

    Integer deleteUserById(long id);
}
