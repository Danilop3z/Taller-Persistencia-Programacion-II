package view;

import enums.ETypeFile;
import model.User;
import persistence.UserRepository;

import java.util.Scanner;

public class LoginView {
    private Scanner sc = new Scanner(System.in);
    UserRepository usr = new UserRepository();

    public LoginView() {}

    public boolean login() {
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
                    usr.loadFile(ETypeFile.SER);
                    System.out.print("Usuario: ");
                    String user = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String pass = sc.nextLine();

                    boolean ok = usr.loginCheck(user, pass);

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
                    User u = new User(user, pass);
                    this.usr.addUser(u);
                    System.out.println("✅ Usuario registrado.");
                    usr.dumpFile(ETypeFile.SER);
                }
            }
        } while (opt != 0);

        return false;
    }
}
