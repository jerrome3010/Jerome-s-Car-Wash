package model;

import java.util.ArrayList;
import java.util.List;

public class Records<T> {
    private List<T> records = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public void add(T record) {
        records.add(record);
        notifyObservers();
    }

    public List<T> getRecords() {
        return records;
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
