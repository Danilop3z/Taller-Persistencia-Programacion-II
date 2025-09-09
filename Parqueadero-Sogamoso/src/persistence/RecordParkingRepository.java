package persistence;

import model.RecordParking;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecordParkingRepository {
    private static final String FILE_PATH = "Parqueadero-Sogamoso/resources/data/" +
            "records.json";

    /**
     * Carga registros de manera manual (parseo de texto plano JSON básico)
     */
    public static List<RecordParking> loadRecords() {
        List<RecordParking> records = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return records;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line.trim());
            }

            String json = jsonBuilder.toString();

            // Elimina [ ] iniciales y finales
            json = json.replace("[", "").replace("]", "").trim();

            if (json.isEmpty()) {
                return records;
            }

            // Divide los objetos { ... }
            String[] objects = json.split("\\},\\{");

            for (String obj : objects) {
                obj = obj.replace("{", "").replace("}", "").trim();

                String[] fields = obj.split(",");

                String plate = "";
                String entry = "";
                String departure = "";
                double total = 0;

                for (String field : fields) {
                    String[] kv = field.split(":");
                    if (kv.length < 2) continue;

                    String key = kv[0].replace("\"", "").trim();
                    String value = kv[1].replace("\"", "").trim();

                    switch (key) {
                        case "licensePlate":
                            plate = value;
                            break;
                        case "entryTime":
                            entry = value;
                            break;
                        case "departureTime":
                            departure = value;
                            break;
                        case "total":
                            total = Double.parseDouble(value);
                            break;
                    }
                }

                records.add(new RecordParking(plate, entry, departure, total));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }

    /**
     * Guarda los registros en un archivo JSON manualmente
     */
    private static void saveRecords(List<RecordParking> records) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            pw.println("[");
            for (int i = 0; i < records.size(); i++) {
                RecordParking r = records.get(i);
                pw.print("  {");
                pw.print("\"licensePlate\":\"" + r.getLicensePlate() + "\",");
                pw.print("\"entryTime\":\"" + r.getEntryTime() + "\",");
                pw.print("\"departureTime\":\"" + r.getDepartureTime() + "\",");
                pw.print("\"total\":" + r.getTotal());
                pw.print("}");
                if (i < records.size() - 1) pw.println(",");
            }
            pw.println("\n]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * CRUD
     */
    public static void addRecord(RecordParking record) {
        List<RecordParking> records = loadRecords();
        records.add(record);
        saveRecords(records);
    }

    public static void deleteRecord(String licensePlate) {
        List<RecordParking> records = loadRecords();
        records.removeIf(r -> r.getLicensePlate().equalsIgnoreCase(licensePlate));
        saveRecords(records);
    }

    public static void updateRecord(RecordParking updated) {
        deleteRecord(updated.getLicensePlate());
        addRecord(updated);
    }

    /**
     * Reportes
     */
    public static long countVehiclesByDay(String date) {
        return loadRecords().stream()
                .filter(r -> r.getEntryTime().startsWith(date))
                .count();
    }

    public static double totalCollectedByDay(String date) {
        return loadRecords().stream()
                .filter(r -> r.getDepartureTime().startsWith(date))
                .mapToDouble(RecordParking::getTotal)
                .sum();
    }
}
