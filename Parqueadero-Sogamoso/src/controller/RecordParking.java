package controller;
import java.util.ArrayList;
import model.Vehicle;

public class RecordParking {
    private String licensePlate;
    private String entryTime;
    private String departureTime;
    private double total;
    
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
   
    public RecordParking(String licensePlate, String entryTime, String departureTime, double total) {
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
        this.departureTime = departureTime;
        this.total = total;
    }

}