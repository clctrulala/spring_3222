package web.service;

import web.model.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    boolean addUser(User user);
    List<User> getUsers();
    boolean updateUser(User user);
    boolean deleteUser(long id);
}
