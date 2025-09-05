package view;
import java.util.Scanner;

import controller.UserHandling;
import model.User;
public class Main {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        UserHandling uss = new UserHandling();
        int a;
        do{
        System.out.println("===Bienvenido, por favor seleccione la acción===");
        System.out.println("1.Ingresar");
        System.out.println("2. Registrarse");
        a = sn.nextInt();

        switch (a) {
            case 2:
                System.out.println("Ingrese Nombre de Usuario");
                String userName = sn.next();
                
                System.out.println("Ingrese la contraseña para el usuario");
                String pass = sn.next();
                User u = new User(userName, pass);
                uss.addUser(u);
                break;
        
            case 1:
                System.out.println();
                break;
        }

    }while (a != 2);       
        
}
}
