package model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<CarWash> carWashes = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public void addCarWash(CarWash carWash) {
        carWashes.add(carWash);
        notifyObservers();
    }

    public List<CarWash> getCarWashes() {
        return carWashes;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
