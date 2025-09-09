package view;

import controller.LoginController;
import model.User;
import persistence.UserRepository;

import java.util.Scanner;

public class LoginView {
    private static Scanner sc = new Scanner(System.in);

    public static boolean login() {
        int opt;
        do {
            System.out.println("\n=== SISTEMA PARQUEADERO ===");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar usuario");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opt = Integer.parseInt(sc.nextLine());

            switch (opt) {
                case 1 -> {
                    System.out.print("Usuario: ");
                    String user = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String pass = sc.nextLine();

                    boolean ok = LoginController.login(user, pass);
                    if (ok) {
                        System.out.println("✅ Acceso permitido.");
                        return true;
                    } else {
                        System.out.println("❌ Usuario o contraseña incorrectos.");
                    }
                }
                case 2 -> {
                    System.out.print("Nuevo usuario: ");
                    String user = sc.nextLine();
                    System.out.print("Nueva contraseña: ");
                    String pass = sc.nextLine();
                    UserRepository.addUser(new User(user, pass));
                    System.out.println("✅ Usuario registrado.");
                }
            }
        } while (opt != 0);

        return false;
    }
}
