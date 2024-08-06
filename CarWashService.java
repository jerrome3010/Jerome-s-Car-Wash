package model;

public class CarWashService {
    private int id;
    private String name;
    private String description;
    private double price;
    private VehicleType vehicleType;

    public CarWashService(int id, String name, String description, double price, VehicleType vehicleType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.vehicleType = vehicleType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPriceForVehicleType() {
        return price;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
