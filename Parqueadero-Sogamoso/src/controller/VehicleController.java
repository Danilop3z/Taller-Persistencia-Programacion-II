package controller;

import model.Vehicle;
import persistence.VehicleRepository;

import java.util.List;

public class VehicleController {
    public static void addVehicle(Vehicle v) {
        VehicleRepository.addVehicle(v);
    }

    public static List<Vehicle> listVehicles() {
        return VehicleRepository.loadVehicles();
    }

    public static void updateVehicle(Vehicle v) {
        VehicleRepository.updateVehicle(v);
    }

    public static void deleteVehicle(String licensePlate) {
        VehicleRepository.deleteVehicle(licensePlate);
    }
}
