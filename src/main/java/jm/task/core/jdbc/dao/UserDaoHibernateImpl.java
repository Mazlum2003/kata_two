package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Hibernateinfo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao{
    static Hibernateinfo userInfo = new Hibernateinfo();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS new_test (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "lastName VARCHAR(255) NOT NULL," +
                "age(3) TINYINT" +
                ")";
        try (Session session = userInfo.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction() ;
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка при создании таблицы: " + e.getMessage());
        }

    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS new_test";
        try (Session session = userInfo.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction() ;
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка при удалении таблицы: " + e.getMessage());
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = userInfo.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User с именем — " + name + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении пользователя: " + e.getMessage());
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = userInfo.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction() ;
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя: " + e.getMessage());
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = userInfo.getSessionFactory().openSession()) {
            return session.createQuery("from jm.task.core.jdbc.model.User", User.class).list();
        } catch (Exception e) {
            System.out.println("Ошибка при получении пользователей: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = userInfo.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction() ;
            session.createQuery("delete from jm.task.core.jdbc.model.User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка при очистке таблицы: " + e.getMessage());
        }

    }
}
