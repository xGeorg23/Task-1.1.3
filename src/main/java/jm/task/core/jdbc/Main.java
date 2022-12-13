package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Andre","A", (byte) 31);
        userService.saveUser("Jon","J", (byte) 1);
        userService.saveUser("Gosha","G", (byte) 22);
        userService.saveUser("Dimon","Ty-py-py-py-py-py", (byte) 127);
        List<User> user = userService.getAllUsers();
        for (User person: user) {
            System.out.println(person);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
