package model;

import java.util.ArrayList;
import java.util.List;

public class CarWash {
    private int id;
    private int stationNumber;
    private Customer customer;
    private Car car;
    private List<CarWashService> services;

    public CarWash(int id, int stationNumber, Customer customer, Car car) {
        this.id = id;
        this.stationNumber = stationNumber;
        this.customer = customer;
        this.car = car;
        this.services = new ArrayList<>();
    }

    public void addService(CarWashService service) {
        services.add(service);
    }

    public List<CarWashService> getServices() {
        return services;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (CarWashService service : services) {
            totalPrice += service.getPriceForVehicleType();
        }
        return totalPrice;
    }
}
