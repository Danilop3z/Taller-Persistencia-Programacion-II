package persistence;

import model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    // Guardamos dentro de resources/data (no en src para evitar líos con compilación)
    private static final String FILE_PATH = "Parqueadero-Sogamoso/resources/data/users.dat";

    /**
     * Guarda la lista completa de usuarios en el archivo
     */
    public static void saveUsers(List<User> users) {
        try {
            File file = new File(FILE_PATH);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                System.out.println("✅ Guardando usuarios en: " + file.getAbsolutePath());
                oos.writeObject(users);
            }
        } catch (IOException e) {
            System.err.println("❌ Error guardando usuarios: " + e.getMessage());
        }
    }

    /**
     * Carga todos los usuarios desde el archivo
     */
    @SuppressWarnings("unchecked")
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return users; // retorna vacío si aún no existe
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            users = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error leyendo usuarios: " + e.getMessage());
        }

        return users;
    }

    /**
     * Busca si el usuario y contraseña coinciden (para login)
     */
    public static boolean validateLogin(String username, String password) {
        return loadUsers().stream()
                .anyMatch(u -> u.getUserName().equals(username) && u.getPassword().equals(password));
    }

    /**
     * Agrega un nuevo usuario al archivo (si no existe)
     */
    public static void addUser(User user) {
        List<User> users = loadUsers();
        boolean exists = users.stream().anyMatch(u -> u.getUserName().equals(user.getUserName()));
        if (!exists) {
            users.add(user);
            saveUsers(users);
        } else {
            System.out.println("⚠️ Usuario ya existe.");
        }
    }
}
