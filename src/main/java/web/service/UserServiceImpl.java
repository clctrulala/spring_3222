package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

//    @Transactional
    @Override
    public boolean addUser(User user) {
        return null != userDao.save(user);
    }

//    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

//    @Transactional
    @Override
    public boolean updateUser(User user) {
        if ( userDao.existsById(user.getId()) ) {
            userDao.save(user);
            return true;
        }
        return false;
    }

//    @Transactional
    @Override
    public boolean deleteUser(long id) {
        if ( userDao.existsById(id) ) {
            userDao.deleteById(id);
            return true;
        }
        return false;
    }
}
