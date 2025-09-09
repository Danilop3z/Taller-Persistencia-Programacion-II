package model;

public class VehicleRate {
    private String typeVehicle; //Moto, Carro, Bici
    private double price; //Precio segun el tipo de vehiculo por hora

    public VehicleRate() {}

    public VehicleRate(String typeVehicle, double price) {
        this.typeVehicle = typeVehicle;
        this.price = price;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "VehicleRate{" +
                "typeVehicle='" + typeVehicle + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
