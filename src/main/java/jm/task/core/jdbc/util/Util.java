package jm.task.core.jdbc.util;

import com.fasterxml.classmate.AnnotationConfiguration;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.swing.text.html.parser.Entity;
import java.sql.*;
import java.util.*;

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
     * Чтобы переподключаясь к базе, корректно добавлять данные.
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

    /**
     * Соединение с базой данных черех Hibernate
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactoryHibernate() {
        if(factory == null) {
            try {
                try {
                    Configuration configuration = new Configuration();
                    configuration.addAnnotatedClass(User.class);

                    Properties settings = new Properties();
                    // Почему без драйвера работает ?????
                    //settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver"); //AvailableSettings

                    // какая разница AvailableSettings & Environment
                    settings.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/my_schema?useSSL=false");
                    settings.put(Environment.USER, "root");
                    settings.put(Environment.PASS, "1rt7");

                    // Что дает диалект ??
                    // settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                    settings.put(Environment.SHOW_SQL, "true");
                    //settings.put(Environment.DEFAULT_SCHEMA, "my_schema");
                    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                    // авто-генерация таблиц ????
                    //settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                    //settings.put(Environment.HBM2DDL_AUTO, SchemaAutoTooling.CREATE.name().toLowerCase());
                    settings.put(Environment.AUTOCOMMIT, "true");

                    configuration.setProperties(settings);

                    serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .build();

                    MetadataSources meta = new MetadataSources(serviceRegistry);
                    Metadata data = meta.getMetadataBuilder().build();
                    factory = data.getSessionFactoryBuilder().build();


                    //factory = configuration.buildSessionFactory(serviceRegistry);

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


    public static EntityManager getEntityManager() {
        EntityManagerFactory entityManager = null;
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

                  entityManager = Persistence
                        .createEntityManagerFactory("JPAUNIT", settings);

                //EntityTransaction transaction = entityManager2.getTransaction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(entityManager).createEntityManager();
    }








    public static EntityManager getE () {


        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                // "jdbc" is the default, but for explicitness
                .applySetting( AvailableSettings.TRANSACTION_COORDINATOR_STRATEGY, "jdbc" )
                .build();

        Metadata metadata = new MetadataSources( serviceRegistry )
                .addAnnotatedClass( User.class )
                .getMetadataBuilder()
                .build();


        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();

        Session session = sessionFactory.openSession();
        try {
            // calls Connection#setAutoCommit( false ) to
            // signal start of transaction
            session.getTransaction().begin();

            session.createQuery( "UPDATE User set name = 'Sir' WHERE id=1")
                    .executeUpdate();

            // calls Connection#commit(), if an error
            // happens we attempt a rollback
            session.getTransaction().commit();
        }
        catch ( Exception e ) {
            // we may need to rollback depending on
            // where the exception happened
            if ( session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK ) {
                session.getTransaction().rollback();
            }
            // handle the underlying error
        }
        finally {
            session.close();
            sessionFactory.close();
        }




//        EntityManagerFactory  entityManagerFactory =
//                new HibernatePersistenceProvider()
//                        .createContainerEntityManagerFactory(archiverPersistenceUnitInfo(),
//                ImmutableMap.<String, Object>builder()
//                        .put(JPA_JDBC_DRIVER, JDBC_DRIVER)
//                        .put(JPA_JDBC_URL, JDBC_URL)
//                        .put(DIALECT, Oracle12cDialect.class)
//                        .put(HBM2DDL_AUTO, CREATE)
//                        .put(SHOW_SQL, false)
//                        .put(QUERY_STARTUP_CHECKING, false)
//                        .put(GENERATE_STATISTICS, false)
//                        .put(USE_REFLECTION_OPTIMIZER, false)
//                        .put(USE_SECOND_LEVEL_CACHE, false)
//                        .put(USE_QUERY_CACHE, false)
//                        .put(USE_STRUCTURED_CACHE, false)
//                        .put(STATEMENT_BATCH_SIZE, 20)
//                        .build());
//
//        entityManager = entityManagerFactory.createEntityManager();




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
        return null;
}
}
