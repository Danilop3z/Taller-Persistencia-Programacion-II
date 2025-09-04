package model;

public class Vehicle {
    private String licensePlate;
    private String typeVehicle;
    private String owner;
    private String model;
    private String color;
    private String pricePerHour;
    
    public Vehicle(String licensePlate, String typeVehicle, String owner, String model, String color,
            String pricePerHour) {
        this.licensePlate = licensePlate;
        this.typeVehicle = typeVehicle;
        this.owner = owner;
        this.model = model;
        this.color = color;
        this.pricePerHour = pricePerHour;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @Override
    public String toString() {
        return "Vehicle [licensePlate=" + licensePlate + ", typeVehicle=" + typeVehicle + ", owner=" + owner
                + ", model=" + model + ", color=" + color + ", pricePerHour=" + pricePerHour + "]";
    }

}