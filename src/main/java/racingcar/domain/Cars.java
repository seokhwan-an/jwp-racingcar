package racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.util.NumberGenerator;

public class Cars {

    private final List<Car> cars;

    public Cars() {
        cars = new ArrayList<>();
    }

    public Cars(List<Car> cars) {
        this.cars = new ArrayList<>(cars);
    }

    public static Cars of(List<String> carNames) {
        List<Car> cars = new ArrayList<>();
        for (String carName : carNames) {
            cars.add(new Car(carName));
        }

        return new Cars(cars);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void moveAll(NumberGenerator numberGenerator) {
        for (Car car : cars) {
            car.move(numberGenerator.generate());
        }
    }

    public List<Car> getAll() {
        return Collections.unmodifiableList(cars);
    }

    private List<Car> findSamePositionCar(Car car) {
        return cars.stream()
                .filter(it -> it.equalsPosition(car))
                .collect(Collectors.toUnmodifiableList());
    }

    private Car findMaxPositionCar() {
        return cars.stream()
                .max(Car::compareTo)
                .orElseThrow();
    }

    public Cars pickWinners() {
        return new Cars(findSamePositionCar(findMaxPositionCar()));
    }
}
