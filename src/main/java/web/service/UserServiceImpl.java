package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Transactional
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Transactional
    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Transactional
    @Override
    public boolean deleteUser(long id) {
        return userDao.deleteUser(id);
    }
}
