package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class CarRepository {
    private final List<Car> carData = new ArrayList<>();

    @Autowired
    private IdGenerator idGenerator;

    public Car create(Car car) {
        if (car.getCarId() == null || car.getCarId().trim().isEmpty()) {
            car.setCarId(idGenerator.generateId());
        }
        carData.add(car);
        return car;
    }

    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    public Car findById(String id) {
        return carData.stream()
                .filter(car -> car.getCarId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Car update(String id, Car updatedCar) {
        Optional<Car> carToUpdate = carData.stream()
                .filter(car -> car.getCarId().equals(id))
                .findFirst();

        if (carToUpdate.isPresent()) {
            Car car = carToUpdate.get();
            updateCarFields(car, updatedCar);
            return car;
        }
        return null;
    }

    // Pemisahan metode untuk update fields (SRP)
    private void updateCarFields(Car target, Car source) {
        target.setCarName(source.getCarName());
        target.setCarColor(source.getCarColor());
        target.setCarQuantity(source.getCarQuantity());
    }

    public void delete(String id) {
        carData.removeIf(car -> car.getCarId().equals(id));
    }
}