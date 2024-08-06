package model;

public class Car {
    private VehicleType vehicleType;
    private String make;
    private String model;
    private String color;
    private String licensePlate;

    public Car(VehicleType vehicleType, String make, String model, String color, String licensePlate) {
        this.vehicleType = vehicleType;
        this.make = make;
        this.model = model;
        this.color = color;
        this.licensePlate = licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public String toString() {
        return make + " " + model + " (" + color + ", " + licensePlate + ")";
    }
}
