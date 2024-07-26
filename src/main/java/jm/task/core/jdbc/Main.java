package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        UserService userService = new UserServiceImpl();

        // Создание таблицы
        userService.createUsersTable();
        System.out.println("Таблица users создана");

        userService.saveUser("Иван", "Иванов", (byte) 25);
        userService.saveUser("Максим", "Максимов", (byte) 30);
        userService.saveUser("Олег", "Олегов", (byte) 35);
        userService.saveUser("Анна", "Анисимова", (byte) 28);

        // Получение всех пользователей
        List<User> users = userService.getAllUsers();
        System.out.println("Все пользователи в базе данных:");
        for (User user : users) {
            System.out.println(user);
        }

        // Очистка таблицы
        userService.cleanUsersTable();
        System.out.println("Таблица users очищена");

        // Удаление таблицы
        userService.dropUsersTable();
        System.out.println("Таблица users удалена");



    }
}








