package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {
    boolean addUser(User user);
    List<User> getUsers();
    boolean updateUser(User user);
    boolean deleteUser(long id);
}
