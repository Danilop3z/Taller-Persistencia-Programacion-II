package view;

import controller.RecordParkingController;
import controller.VehicleController;
import model.RecordParking;
import model.Vehicle;

import java.util.Scanner;

public class MenuView {
    private static Scanner sc = new Scanner(System.in);

    public static void showMenu() {
        int option;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. CRUD Vehículos");
            System.out.println("2. CRUD Parqueo");
            System.out.println("3. Reportes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1 -> vehicleMenu();
                case 2 -> parkingMenu();
                case 3 -> reportMenu();
            }
        } while (option != 0);
    }

    private static void vehicleMenu() {
        int opt;
        do {
            System.out.println("\n--- CRUD Vehículos ---");
            System.out.println("1. Agregar vehículo");
            System.out.println("2. Listar vehículos");
            System.out.println("3. Actualizar vehículo");
            System.out.println("4. Eliminar vehículo");
            System.out.println("0. Volver");
            opt = Integer.parseInt(sc.nextLine());

            switch (opt) {
                case 1 -> {
                    System.out.print("Placa: ");
                    String plate = sc.nextLine();
                    System.out.print("Tipo (Carro/Moto/Bicicleta): ");
                    String type = sc.nextLine();
                    System.out.print("Dueño: ");
                    String owner = sc.nextLine();
                    System.out.print("Modelo: ");
                    String model = sc.nextLine();
                    System.out.print("Color: ");
                    String color = sc.nextLine();

                    Vehicle v = new Vehicle(plate, type, owner, model, color);
                    VehicleController.addVehicle(v);
                    System.out.println("✅ Vehículo agregado.");
                }
                case 2 -> VehicleController.listVehicles().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Placa a actualizar: ");
                    String plate = sc.nextLine();
                    System.out.print("Nuevo tipo: ");
                    String type = sc.nextLine();
                    System.out.print("Nuevo dueño: ");
                    String owner = sc.nextLine();
                    System.out.print("Nuevo modelo: ");
                    String model = sc.nextLine();
                    System.out.print("Nuevo color: ");
                    String color = sc.nextLine();

                    Vehicle v = new Vehicle(plate, type, owner, model, color);
                    VehicleController.updateVehicle(v);
                    System.out.println("✅ Vehículo actualizado.");
                }
                case 4 -> {
                    System.out.print("Placa a eliminar: ");
                    String plate = sc.nextLine();
                    VehicleController.deleteVehicle(plate);
                    System.out.println("✅ Vehículo eliminado.");
                }
            }
        } while (opt != 0);
    }

    private static void parkingMenu() {
        int opt;
        do {
            System.out.println("\n--- CRUD Parqueo ---");
            System.out.println("1. Agregar registro");
            System.out.println("2. Listar registros");
            System.out.println("3. Actualizar registro");
            System.out.println("4. Eliminar registro");
            System.out.println("0. Volver");
            opt = Integer.parseInt(sc.nextLine());

            switch (opt) {
                case 1 -> {
                    System.out.print("Placa: ");
                    String plate = sc.nextLine();
                    System.out.print("Fecha entrada (DD/MM/YYYY HH:mm): ");
                    String entry = sc.nextLine();
                    System.out.print("Fecha salida (DD/MM/YYYY HH:mm): ");
                    String departure = sc.nextLine();
                    System.out.print("Total: ");
                    double total = Double.parseDouble(sc.nextLine());

                    RecordParking r = new RecordParking(plate, entry, departure, total);
                    RecordParkingController.addRecord(r);
                    System.out.println("✅ Registro agregado.");
                }
                case 2 -> RecordParkingController.listRecords().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Placa: ");
                    String plate = sc.nextLine();
                    System.out.print("Nueva fecha entrada: ");
                    String entry = sc.nextLine();
                    System.out.print("Nueva fecha salida: ");
                    String departure = sc.nextLine();
                    System.out.print("Nuevo total: ");
                    double total = Double.parseDouble(sc.nextLine());

                    RecordParking r = new RecordParking(plate, entry, departure, total);
                    RecordParkingController.updateRecord(r);
                    System.out.println("✅ Registro actualizado.");
                }
                case 4 -> {
                    System.out.print("Placa a eliminar: ");
                    String plate = sc.nextLine();
                    RecordParkingController.deleteRecord(plate);
                    System.out.println("✅ Registro eliminado.");
                }
            }
        } while (opt != 0);
    }

    private static void reportMenu() {
        System.out.print("Ingrese fecha (DD/MM/YYYY): ");
        String date = sc.nextLine();

        long count = RecordParkingController.countByDay(date);
        double total = RecordParkingController.totalByDay(date);

        System.out.println("📊 Vehículos ingresados: " + count);
        System.out.println("💰 Total recaudado: " + total);
    }
}
