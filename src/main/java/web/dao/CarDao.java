package web.dao;

import web.model.Car;
import java.util.List;

public interface CarDao {
    void add(Car car);
    List<Car> getAllCars();
    List<Car> getCars(int count);
}
