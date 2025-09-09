package controller;

import persistence.UserRepository;

public class LoginController {
    public static boolean login(String username, String password) {
        return UserRepository.validateLogin(username, password);
    }
}
