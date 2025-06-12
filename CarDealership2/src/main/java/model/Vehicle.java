package model;

public class Vehicle {
    private int vin;
    private String make;
    private String model;
    private String color;
    private int year;
    private String vehicleType;
    private int mileage;
    private double price;
    private boolean sold;

    public Vehicle(int vin, String make, String model, String color, int year, String vehicleType, int mileage, double price, boolean sold) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.vehicleType = vehicleType;
        this.mileage = mileage;
        this.price = price;
        this.sold = sold;
    }

    public int getVin() {
        return vin;
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

    public int getYear() {
        return year;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getMileage() {
        return mileage;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSold() {
        return sold;
    }

    @Override
    public String toString() {
        return String.format(
                "VIN:%d | %d %s %s | %s | %s | %d mi | $%.2f | sold=%b",
                vin, year, make, model, color, vehicleType, mileage, price, sold
        );
    }
}
