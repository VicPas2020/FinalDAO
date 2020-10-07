package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.One_to_one_BiDirect.Passport;
import jm.task.core.jdbc.model.One_to_one_BiDirect.Person;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class PersonDaoHibernateImpl implements UserDao {

    private final String tableName;

    Session session;

    {
        session = Util.createSessionHIBER();
    }

    public PersonDaoHibernateImpl(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + tableName +
                " (id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(20) NOT NULL, " +
                "lastName VARCHAR(20) NOT NULL, " +
                "age TINYINT NOT NULL DEFAULT 0, PRIMARY KEY (id) ) ";

        session.beginTransaction();
        session.createNativeQuery(query).executeUpdate();
        session.getTransaction().commit();
     }

    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS " + tableName;

        session.beginTransaction();
        session.createSQLQuery(query).executeUpdate();
        session.getTransaction().commit();
    }



    @Override
    public void savePerson(Person person) {
        //Person person = new Person(name, lastName ,age, pass);
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
    }


    @Override
    public void removeUserById(long id) {

        if (session.find(Person.class, id) != null) {
            session.beginTransaction();
            session.delete(session.find(Person.class, id));
            session.getTransaction().commit();
        } else {
            System.out.println("Индекс " + id + " не существует");
        }
    }


//    @Override
//    public void removeUserById(long id) {
//
//        if (session.find(User.class, id) != null) {
//            session.beginTransaction();
//            session.delete(session.find(User.class, id));
//            session.getTransaction().commit();
//        } else {
//            System.out.println("Индекс " + id + " не существует");
//        }
//    }

//    @Override
//    public void removeUserById(long id) {
//
//        if (session.find(User.class, id) != null) {
//            session.beginTransaction();
//            session.delete(session.find(User.class, id));
//            session.getTransaction().commit();
//        } else {
//            System.out.println("Индекс " + id + " не существует");
//        }
//    }

    @Override
    public List<User> getAllUsers() {

        String query = "FROM User";

        session.beginTransaction();
        List<User> list = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE " + tableName;

        session.beginTransaction();
        session.createSQLQuery(query).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public Session openConnection() {
        return Util.createSessionHIBER();
    }

    @Override
    public void closeConnection() {
        Util.closeHIBER();
    }




    @Override
    public void savePassport(Passport passport) {
        throw new RuntimeException("пустой метод 7");
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        throw new RuntimeException("пустой метод 8");
    }

}
