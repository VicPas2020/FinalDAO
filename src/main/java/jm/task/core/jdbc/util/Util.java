package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Util {

    // JDBC
    private static final String url = "jdbc:mysql://localhost:3306/my_schema?useSSL=false";
    private static final String user = "root";
    private static final String password = "1rt7";

    /**
     * Обработка запросов типа NOT SELECT к базе данных
     * @param query - NOT SELECT SQL запрос
     */
    public static void connectUpdate(String query) {
        try (  Connection con = DriverManager.getConnection(url, user, password) ;
               Statement stmt = con.createStatement() )
            {  stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    /**
     * Обработка запросов типа SELECT к базе данных
     * @param query  - SELECT SQL запрос
     * @return - Лист объектов базы данных
     */
    public static List<User> connectSelect(String query) {

        List<User> list = new ArrayList<>();

        try ( Connection con = DriverManager.getConnection(url, user, password);
              Statement  stmt = con.createStatement();
              ResultSet rs = stmt.executeQuery(query)
        )
        {
            while (rs.next()) {
                int    id       = rs.getInt   (1);
                String name     = rs.getString(2);
                String lastName = rs.getString(3);
                byte   age      = rs.getByte  (4);

                User user = new User(name,lastName,age);
                list.add(user);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return list;
    }

    /**
     * Нужен для того, чтобы знать последний порядковый номер объекта в базе данных.
     *
     * @return номер последнего объекта в таблице
     */
    public static int counter() {
        int res=-1;
        String s = "SELECT MAX(id) FROM new_table3";
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs   = stmt.executeQuery(s))
        {
            rs.next();
            res = rs.getInt(1);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return res;
    }

    // HIBERNATE
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory getSessionFactoryHibernate() {
        if(factory == null) {
            try {
                try {
                    Configuration configuration = new Configuration();
                    configuration.addAnnotatedClass(User.class);

                    Properties settings = new Properties();
                    // Почему без драйвера работает ?????
                    //settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver"); //AvailableSettings

                    settings.put(Environment.URL, "jdbc:mysql://localhost:3306/my_schema?useSSL=false");
                    settings.put(Environment.USER, "root");
                    settings.put(Environment.PASS, "1rt7");

                    // Что дает диалект ??
                    // settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                    settings.put(Environment.SHOW_SQL, "true");
                    // settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                    // авто-генерация таблиц ????
                    //settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                    //settings.put(Environment.HBM2DDL_AUTO, SchemaAutoTooling.CREATE.name().toLowerCase());
                    settings.put(Environment.AUTOCOMMIT, "true");

                    configuration.setProperties(settings);

                    serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();

                    factory = configuration.buildSessionFactory(serviceRegistry);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                if(serviceRegistry != null) {
                    StandardServiceRegistryBuilder.destroy(serviceRegistry);
                }
            }
        }
        return factory;
    }

    // HIBERNATE

//    hibernate.connection.driver_class=com.mysql.jdbc.Driver
//    hibernate.connection.url= jdbc:mysql://localhost:3306/dealer
//    hibernate.connection.username=root
//    hibernate.connection.password=root
//    hibernate.connection.pool_size=1
//    hibernate.transaction.factory_class = org.hibernate.transaction.JTATransactionFactory
//    hibernate.transaction.manager_lookup_class = org.hibernate.transaction.JBossTransactionManagerLookup
//    hibernate.dialect = org.hibernate.dialect.MySQLDialect
//    hibernate.packagesToScan = domain
//    hibernate.autocommit = true
}
