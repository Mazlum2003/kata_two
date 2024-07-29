package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDao userDaoHib = new UserDaoHibernateImpl();

        userDaoHib.createUsersTable();
        System.out.println("Таблица users создана");

        userDaoHib.saveUser("Иван", "Иванов", (byte) 25);
        userDaoHib.saveUser("Максим", "Максимов", (byte) 30);
        userDaoHib.saveUser("Олег", "Олегов", (byte) 35);
        userDaoHib.saveUser("Анна", "Анисимова", (byte) 28);

        // Получение всех пользователей
        List<User> users = userDaoHib.getAllUsers();
        System.out.println("Все пользователи в базе данных:");
        for (User user : users) {
            System.out.println(user);
        }

        // Очистка таблицы
        userDaoHib.cleanUsersTable();
        System.out.println("Таблица users очищена");

        // Удаление таблицы
        userDaoHib.dropUsersTable();
        System.out.println("Таблица users удалена");




    }
}








