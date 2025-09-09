package persistence;

import model.VehicleRate;
import java.io.*;
import java.util.*;

public class VehicleRateRepository {
    private static final String FILE_PATH = "Parqueadero-Sogamoso/resources/data/vehiclerates.csv";

    /**
     * Carga todas las tarifas desde el archivo CSV
     */
    public static List<VehicleRate> loadRates() {
        List<VehicleRate> rates = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    String type = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    rates.add(new VehicleRate(type, price));
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo tarifas: " + e.getMessage());
        }
        return rates;
    }

    /**
     * Devuelve el precio por tipo de vehículo
     */
    public static double getPriceByType(String type) {
        return loadRates().stream()
                .filter(rate -> rate.getTypeVehicle().equalsIgnoreCase(type))
                .map(VehicleRate::getPrice)
                .findFirst()
                .orElse(0.0); // si no encuentra, devuelve 0.0
    }
    /**
     * Devuelve todos los tipos de vehículos disponibles
     */
    public static Set<String> getAvailableTypes() {
        Set<String> types = new HashSet<>();
        for (VehicleRate rate : loadRates()) {
            types.add(rate.getTypeVehicle());
        }
        return types;
    }
}

