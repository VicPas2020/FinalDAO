package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Util  {

    static Connection connection;
    static Statement statement;

    public static Connection createconnectionJDBC()   {

        final String url = "jdbc:mysql://localhost:3306/my_schema?useSSL=false";
        final String user = "root";
        final String password = "1rt7";

        try {
            System.out.println("соединение.");

            connection = DriverManager.getConnection(url, user, password);
            // TODO: connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            // TODO: connection.setAutoCommit(false);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Не удалось установить соединение.");
        }
        return connection;
    }

    public static void closeJDBC(){
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public static Session createSessionHIBER()   {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        Properties settings = new Properties();
        //settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver"); //AvailableSettings
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/my_schema?useSSL=false");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "1rt7");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.AUTOCOMMIT, "true");

        configuration.setProperties(settings);

        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        factory = configuration.buildSessionFactory(serviceRegistry);
        return factory.openSession();

    }

    public static void closeHIBER(){
        if (factory != null) {
            factory.close();
            System.out.println("factory closed");
        }

        if (serviceRegistry != null) {
            serviceRegistry.close();
            System.out.println("registry closed");
        }
    }
}
