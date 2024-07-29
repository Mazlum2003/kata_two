package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class Hibernateinfo {

    public SessionFactory getSessionFactory() {
        Properties properties = new Properties();
                   properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                   properties.put(Environment.URL, "jdbc:mysql://localhost:3306/new_test");
                   properties.put(Environment.USER, "root");
                   properties.put(Environment.PASS, "root");
                   properties.put(Environment.SHOW_SQL, true);
                   properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                   properties.put(Environment.HBM2DDL_AUTO, "update");


        return new Configuration()
                          .setProperties(properties)
                          .addAnnotatedClass(User.class)
                          .buildSessionFactory();
    }



}
