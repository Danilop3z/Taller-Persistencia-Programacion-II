package controller;

import java.util.ArrayList;

import model.User;

public class UserHandling {

        private ArrayList<User> users = new ArrayList<>();

        public UserHandling(ArrayList users){
                this.users = users;
        }
      
        //Agregar Usuarios
        public void addUser(User u) { 
                users.add(u);
                System.out.println("!Usuario Registrado¡");
        }

        //Listar Usuarios
        public void listUser() {
                if (users.isEmpty()) {
                        System.out.println("No hay Usuarios Registrados.");
                } else {
                        for (User u : users) {
                                System.out.println(u);
                        }
                }
        }
       
        //Buscar Usuario
        public User searchUser(String userName) {
                for(User u : users) {
                        if(u.getUserName() == userName) {
                                return u;
                        }
                }
                                return null; //No se encuentra Usuario
        } 
      
        //Actualizar
        public boolean updateUser(String userName, String newUserName) {
                User u = searchUser(userName);
                if (u != null) {
                        u.setUserName(newUserName);
                        System.out.println("Nombre de Usuario Actualizado");
                        return true;
                }
                return false;
        }

        //Eliminar
        public boolean deleteUser(String userName) {
                User u = searchUser(userName);
                if (u != null) {
                        users.remove(u);
                        System.out.println("El usuario " + u.getUserName() + "Ha sido eliminado con Exito!");
                        return true;
                }
                        return false;
        }
}


