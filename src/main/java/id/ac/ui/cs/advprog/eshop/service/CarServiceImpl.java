package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car) {
        String validationError = validateCar(car);
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);
        }
        carRepository.create(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findById(String carId) {
        Car car = carRepository.findById(carId);
        return car;
    }

    @Override
    public void update(String carId, Car car) {
        String validationError = validateCar(car);
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);
        }
        carRepository.update(carId, car);
    }

    @Override
    public void deleteCarById(String carId) {
        carRepository.delete(carId);
    }

    @Override
    public String validateCar(Car car) {
        if (car.getCarName() == null || car.getCarName().trim().isEmpty()) {
            return "Car name cannot be empty";
        }
        if (car.getCarQuantity() <= 0) {
            return "Quantity must be greater than 0";
        }
        if (car.getCarColor() == null || car.getCarColor().trim().isEmpty()) {
            return "Car color cannot be empty";
        }
        return null; // Return null if validation passes
    }
}