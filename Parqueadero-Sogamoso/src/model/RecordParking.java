package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecordParking {
    private String licensePlate;
    private String entryTime; //formato DD/MM/YYYY
    private String departureTime; //formato DD/MM/YYYY
    private double total; //calculado con horas de uso * pricePerHour

    public RecordParking() {}

    public RecordParking(String licensePlate, String entryTime, String departureTime, double total) {
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
        this.departureTime = departureTime;
        this.total = total;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    @Override
    public String toString() {
        return "RecordParking{" +
                "licensePlate='" + licensePlate + '\'' +
                ", entryTime='" + entryTime + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", total=" + total +
                '}';
    }
}