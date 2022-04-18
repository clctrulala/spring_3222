package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.CarDao;
import web.model.Car;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Transactional
    @Override
    public void add(Car car) {
        carDao.add(car);
    }

    @Transactional
    @Override
    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    @Transactional
    @Override
    public  List<Car> getCars(int count) {
        return carDao.getCars(count);
    }
}
