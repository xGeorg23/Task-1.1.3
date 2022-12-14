package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Andre","A", (byte) 31);
        userService.saveUser("Jon","J", (byte) 1);
        userService.saveUser("Gosha","G", (byte) 22);
        userService.saveUser("Dimon","Ty-py-py-py-py-py", (byte) 127);
        userService.getAllUsers().stream().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
