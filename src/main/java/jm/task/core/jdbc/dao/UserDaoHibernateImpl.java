package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import javax.persistence.TypedQuery;
import java.util.List;

//import static jm.task.core.jdbc.util.Util.getSessionFactory;
import static jm.task.core.jdbc.util.Util.getSessionFactoryHibernate;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    final String table_name = "new_table3";

    @Override
    public void createUsersTable() {
        try  (Session session = getSessionFactoryHibernate().openSession())  {

            session.beginTransaction();

            String query = "CREATE TABLE IF NOT EXISTS " + table_name +
                    " (id INT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "lastName VARCHAR(20) NOT NULL, " +
                    "age TINYINT NOT NULL DEFAULT 0, PRIMARY KEY (id) ) ";
            // Не смог найти как создать таблицу сразу с авто-инкрементом
            String query2 = "ALTER TABLE new_table3 MODIFY COLUMN id INT auto_increment";

            // это были варианты....
            //GENERATED ALWAYS AS IDENTITY
            // ENGINE=InnoDB
            // INCREMENT (1,1)


            // @Deprecated ???
//            Query q =
//                    session.createSQLQuery(query);
//            q.executeUpdate();

            // ТАк и не понял разницу между createQuery.createSQLQuery.createNativeQuery
            session.createNativeQuery(query).executeUpdate();
            session.createNativeQuery(query2).executeUpdate();
            session.getTransaction().commit();
        }
     }

    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS " + table_name;

        try  (Session session = getSessionFactoryHibernate().openSession())  {
            session.beginTransaction();
            // @Deprecated Чуто тут нужно вместо Query
            Query q = session.createSQLQuery(query);
            q.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try  (Session session = getSessionFactoryHibernate().openSession()) {

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
        try  (Session session = getSessionFactoryHibernate().openSession()) {

            session.beginTransaction();
            User a = new User();
            a.setId(id);
            session.delete(a);
            session.getTransaction().commit();

        }

    }

    @Override
    public List<User> getAllUsers() {

        String qq = "FROM User";

        try  (Session session = getSessionFactoryHibernate().openSession())  {
            // с этим есть проблемы???
//          session.getTransaction().begin();
            session.beginTransaction();
            // Почему createQuery из 2х разных интерфейсов : SharedSessionContract и Session ?
            TypedQuery<User> query2 = session.createQuery(qq /*, User.class*/);

            List<User> results = query2.getResultList();
            session.getTransaction().commit();
            // распечатываем тут, чтобы в Main все было единообразно.
            System.out.println(results);
            return results;
        }
    }

    @Override
    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE " + table_name;
        try  (Session session = getSessionFactoryHibernate().openSession())  {

            session.beginTransaction();
            // @Deprecated
            Query q = session.createSQLQuery(query);
            q.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
