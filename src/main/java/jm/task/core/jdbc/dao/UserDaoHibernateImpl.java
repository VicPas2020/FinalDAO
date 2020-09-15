package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import javax.persistence.TypedQuery;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;
import static jm.task.core.jdbc.util.Util.getSessionFactoryHibernate;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    final String table_name = "new_table3";

    @Override
    public void createUsersTable() {
        try  (Session session = getSessionFactoryHibernate().openSession())  {

            session.beginTransaction();

// CREATE
            
            
//            String query =
//                    "CREATE TABLE IF NOT EXISTS comments" +
//                            "(    id INT  DEFAULT 1," +
//                            "     name varchar(30)," +
//                            "     lastName varchar(100)," +
//                            "     com_no int" +
//                            ");";




            
            String query = "CREATE TABLE IF NOT EXISTS " + table_name +
                    " (id INT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "lastName VARCHAR(20) NOT NULL, " +
                    "age TINYINT NOT NULL DEFAULT 0, PRIMARY KEY (id) ) ";

            String s2 = "ALTER TABLE new_table3 MODIFY COLUMN id INT auto_increment";

        //GENERATED ALWAYS AS IDENTITY
            // ENGINE=InnoDB
            // @Deprecated
//            Query q =
//                    session.createSQLQuery(query);
//            q.executeUpdate();

            session.createNativeQuery(query).executeUpdate();
            session.createNativeQuery(s2).executeUpdate();
//            session.createSQLQuery(query).executeUpdate();
//            session.createQuery(query).executeUpdate();



            session.getTransaction().commit();
        }

        }

    @Override
    public void dropUsersTable() {

        try  (Session session = getSessionFactoryHibernate().openSession())  {

            session.beginTransaction();

// DROP
            String query = "DROP TABLE IF EXISTS " + table_name;

            // @Deprecated
            Query q =
                    session.createSQLQuery(query);
            q.executeUpdate();

            session.getTransaction().commit();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try  (Session session = getSessionFactory().openSession()) {

            session.beginTransaction();
            User a = new User();

            a.setName(name);
            a.setLastName(lastName);
            a.setAge(age);

            session.saveOrUpdate(a);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try  (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            User foo = new User();
            foo.setId(id);
            session.delete(foo);
            session.getTransaction().commit();

        }

    }

    @Override
    public List<User> getAllUsers() {

        String qq = "FROM User";

        try  (Session session = getSessionFactory().openSession())  {

//            session.getTransaction().begin();
             session.beginTransaction();

            TypedQuery<User> query2 = session.createQuery(
                qq/* , User.class*/);

        List<User> results = query2.getResultList();

         session.getTransaction().commit();
        System.out.println(results);

        return results;

    }

    }

    @Override
    public void cleanUsersTable() {

        try  (Session session = getSessionFactoryHibernate().openSession())  {

            session.beginTransaction();

// DROP
            String query = "TRUNCATE TABLE " + table_name;

            // @Deprecated
            Query q =
                    session.createSQLQuery(query);
            q.executeUpdate();

            session.getTransaction().commit();
        }
    }
}
