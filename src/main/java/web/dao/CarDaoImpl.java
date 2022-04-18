package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return sessionFactory.getCurrentSession().createQuery("from Car", Car.class).getResultList();
    }

    @Override
    public List<Car> getCars(int count) {
        return sessionFactory.getCurrentSession().createQuery("from Car", Car.class).setMaxResults(count).getResultList();
    }
}
