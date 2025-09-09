package persistence;

import model.Vehicle;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
    private static final String FILE_PATH = "Parqueadero-Sogamoso/resources/data/vehicles.xml";

    /**
     * Crea el archivo XML si no existe
     */
    private static void createFileIfNotExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();

                Element rootElement = doc.createElement("Vehicles");
                doc.appendChild(rootElement);

                saveDocument(doc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Guarda un Document XML en disco
     */
    private static void saveDocument(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(FILE_PATH));
        transformer.transform(source, result);
    }

    /**
     * Carga todos los vehículos
     */
    public static List<Vehicle> loadVehicles() {
        createFileIfNotExists();
        List<Vehicle> vehicles = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(FILE_PATH));
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Vehicle");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;

                    String licensePlate = e.getElementsByTagName("licensePlate").item(0).getTextContent();
                    String typeVehicle = e.getElementsByTagName("typeVehicle").item(0).getTextContent();
                    String owner = e.getElementsByTagName("owner").item(0).getTextContent();
                    String model = e.getElementsByTagName("model").item(0).getTextContent();
                    String color = e.getElementsByTagName("color").item(0).getTextContent();
                    double pricePerHour = Double.parseDouble(e.getElementsByTagName("pricePerHour").item(0).getTextContent());

                    vehicles.add(new Vehicle(licensePlate, typeVehicle, owner, model, color, pricePerHour));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    /**
     * Agrega un vehículo nuevo
     */
    public static void addVehicle(Vehicle vehicle) {
        createFileIfNotExists();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(FILE_PATH));

            Element root = doc.getDocumentElement();

            Element vElement = doc.createElement("Vehicle");

            Element plate = doc.createElement("licensePlate");
            plate.appendChild(doc.createTextNode(vehicle.getLicensePlate()));
            vElement.appendChild(plate);

            Element type = doc.createElement("typeVehicle");
            type.appendChild(doc.createTextNode(vehicle.getTypeVehicle()));
            vElement.appendChild(type);

            Element owner = doc.createElement("owner");
            owner.appendChild(doc.createTextNode(vehicle.getOwner()));
            vElement.appendChild(owner);

            Element model = doc.createElement("model");
            model.appendChild(doc.createTextNode(vehicle.getModel()));
            vElement.appendChild(model);

            Element color = doc.createElement("color");
            color.appendChild(doc.createTextNode(vehicle.getColor()));
            vElement.appendChild(color);

            Element price = doc.createElement("pricePerHour");
            price.appendChild(doc.createTextNode(String.valueOf(vehicle.getPricePerHour())));
            vElement.appendChild(price);

            root.appendChild(vElement);

            saveDocument(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un vehículo por placa
     */
    public static void deleteVehicle(String licensePlate) {
        createFileIfNotExists();

        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(new File(FILE_PATH));

            NodeList nList = doc.getElementsByTagName("Vehicle");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    String plate = e.getElementsByTagName("licensePlate").item(0).getTextContent();

                    if (plate.equalsIgnoreCase(licensePlate)) {
                        e.getParentNode().removeChild(e);
                        break;
                    }
                }
            }

            saveDocument(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza los datos de un vehículo (se reemplaza por la placa)
     */
    public static void updateVehicle(Vehicle updatedVehicle) {
        deleteVehicle(updatedVehicle.getLicensePlate());
        addVehicle(updatedVehicle);
    }
}

