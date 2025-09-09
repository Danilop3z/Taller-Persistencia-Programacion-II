import view.LoginView;
import view.MenuView;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA PARQUEADERO ===");

        if (LoginView.login()) {
            MenuView.showMenu();
        } else {
            System.out.println("Saliendo del sistema...");
        }
    }
}
