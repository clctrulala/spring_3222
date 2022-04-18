package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.List;
import java.util.Optional;

@Controller
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping(value = "/cars{count}")
    public String printCarCount(@RequestParam(value = "count", required = false) Optional<Integer> count, ModelMap model) {
        final int carThreshold = 5;
        List<Car> cars = carThreshold <= count.orElse(5) ? carService.getAllCars() : carService.getCars( Math.abs( count.get() ) );

        model.addAttribute( "cars", cars );
        return "cars";
    }
}
