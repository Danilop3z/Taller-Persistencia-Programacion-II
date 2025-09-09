package controller;

import model.RecordParking;
import persistence.RecordParkingRepository;

import java.util.List;

public class RecordParkingController {
    public static void addRecord(RecordParking r) {
        if (hayCupoDisponible()) {
            RecordParkingRepository.addRecord(r);
            System.out.println("✅ Registro agregado.");
        } else {
            System.out.println("❌ No hay cupos disponibles.");
        }
    }

    public static List<RecordParking> listRecords() {
        return RecordParkingRepository.loadRecords();
    }

    public static void updateRecord(RecordParking r) {
        RecordParkingRepository.updateRecord(r);
    }

    public static void deleteRecord(String plate) {
        RecordParkingRepository.deleteRecord(plate);
    }

    public static long countByDay(String date) {
        return RecordParkingRepository.countVehiclesByDay(date);
    }

    public static double totalByDay(String date) {
        return RecordParkingRepository.totalCollectedByDay(date);
    }

    /**
     * Valida si aún hay cupos disponibles
     */
    private static boolean hayCupoDisponible() {
        int maxCupos = PropertiesConfig.getMaxCupos();

        // Contar registros que no tienen departureTime asignado (vehículos dentro)
        long ocupados = RecordParkingRepository.loadRecords().stream()
                .filter(r -> r.getDepartureTime() == null || r.getDepartureTime().isBlank())
                .count();

        return ocupados < maxCupos;
    }
}

