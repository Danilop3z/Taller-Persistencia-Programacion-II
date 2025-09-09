package persistence;

import model.User;
import interfaces.IActionsFile;
import enums.ETypeFile;
import constants.CommonConstants;
import model.Vehicle;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class VehicleRepository extends FilePlain implements IActionsFile{

        private List<Vehicle> listVehicles;

        public VehicleRepository() {
            this.listVehicles = new ArrayList<Vehicle>();
        }

    @Override
    public void loadFile(ETypeFile eTypeFile) {

    }

    @Override
    public void dumpFile(ETypeFile eTypeFile) {

    }
    private void dumpFileXML() {
        String rutaArchivo = config.getPathFiles().concat(
                config.getNameFileXML());
        List<String> records = new ArrayList<>();
        records.add("<XML version=\"1.0\" encoding=\"UTF-8\">");
        for (Vehicle vehicle = this.listVehicles) {
            records.add("<vehicle>");
            records.add("\t<licensePlate>"+vehicle.getLicensePlate()"</licensePlate>");
            records.add("\t<typeVehicle>"+vehicle.getTypeVehicle()+"</typeVehicle>");
            records.add("\t<owner>"+vehicle.getOwner()+"</owner>");
            records.add("\t<model>"+vehicle.getModel()+"</model>");
            records.add("\t<color>"+vehicle.getColor()+"</color>");
            records.add("\t<pricePerHour>"+vehicle.getPricePerHour()+"</pricePerHour>");
            records.add("</vehicle>");
        }
        records.add("</XML>");
        this.writer(rutaArchivo, records);
    }

}
