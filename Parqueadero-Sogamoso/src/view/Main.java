package view;
import java.util.Scanner;

import controller.UserHandling;
import model.User;
public class Main {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        UserHandling uss = new UserHandling();
        int a;
        int b;
        boolean login = false;
        do {
            System.out.println("===Bienvenido, por favor seleccione la acción===");
            System.out.println("1.Registrarse");
            System.out.println("2.Ingresar");
            System.out.println("0.Salir");
            a = sn.nextInt();
            sn.nextLine();
            switch (a) {
                case 1:
                    System.out.println("Cree su Nombre de Usuario");
                    String userName = sn.nextLine();
                    System.out.println("Cree una contraseña para el usuario");
                    String pswd = sn.next();
                    User u = new User(userName, pswd);
                    uss.addUser(u);
                    break;
                case 2:
                    do {
                        System.out.println("===== LOGIN =====");
                        System.out.print("Usuario: ");
                        String user = sn.nextLine();
                        System.out.print("Contraseña: ");
                        String pass = sn.nextLine();
                        if (uss.login(user, pass)) {
                            System.out.println("✅ Login exitoso. Bienvenido " + user);
                            login = true;
                            do {
                            System.out.println("===Que desea hacer el día de hoy?===");
                            System.out.println("1.Ingresar Vehiculo");
                            System.out.println("2.Verificar Vehiculo");
                            System.out.println("3.Modificar Vehiculo");
                            System.out.println("4.Sacar Vehiculo");
                            System.out.println("5.Reporte de ingresos");
                            System.out.println("0.Salir");
                            b = sn.nextInt();
                            sn.nextLine();
                            switch (b) {
                                case 1:
                                    System.out.println("Opcion 1");//ingresar vehiculo
                                    break;
                                case 2:
                                    System.out.println("Opcion 2");//verificar vehiculo
                                    break;
                                case 3:
                                    System.out.println("Opcion 3");//modificar vehiculo
                                    break;
                                case 4:
                                    System.out.println("Opcion 4");//sacar vehiculo
                                    break;
                                case 5:
                                    System.out.println("Opcion 5");//reporte de ingresos
                                    break;
                            }
                            }while (b != 0);
                            break;
                        } else {
                            System.out.println("❌ Usuario o contraseña incorrectos.");
                        }
                    }while(!login);
            }

        }while (a != 0) ;
    }
}
