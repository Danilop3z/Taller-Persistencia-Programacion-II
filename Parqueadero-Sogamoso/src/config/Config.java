package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Config {
    private static Config config;

    private String pathFiles;

    private String nameFileCSV;

    private String nameFileJSON;

    private String nameFileXML;

    private String nameFileSER;

    private Properties properties;

    private Config() {
        this.properties = new Properties();
        try (FileInputStream entrada = new
                FileInputStream("resources/config/appconfig.properties")) {
            properties.load(entrada);
            this.pathFiles = properties.getProperty("files");
            this.nameFileCSV = properties.getProperty("csv");
            this.nameFileSER = properties.getProperty("ser");
            this.nameFileXML = properties.getProperty("xml");
            this.nameFileJSON = properties.getProperty("json");

        }catch (IOException ex) {
            System.err.println("Error al cargar el archivo properties de configuracion: " + ex.getMessage());
        }
    }

    public static Config getInstance() {
        if (Objects.isNull(config)) {
            config = new Config();
        }
        return config;
    }

    public String getPathFiles() {
        return pathFiles;
    }

    public void setPathFiles(String pathFiles) {
        this.pathFiles = pathFiles;
    }

    public String getNameFileCSV() {
        return nameFileCSV;
    }

    public void setNameFileCSV(String nameFileCSV) {
        this.nameFileCSV = nameFileCSV;
    }

    public String getNameFileJSON() {
        return nameFileJSON;
    }

    public void setNameFileJSON(String nameFileJSON) {
        this.nameFileJSON = nameFileJSON;
    }

    public String getNameFileXML() {
        return nameFileXML;
    }

    public void setNameFileXML(String nameFileXML) {
        this.nameFileXML = nameFileXML;
    }

    public String getNameFileSER() {
        return nameFileSER;
    }

    public void setNameFileSER(String nameFileSER) {
        this.nameFileSER = nameFileSER;
    }
}
