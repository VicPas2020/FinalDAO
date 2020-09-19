package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.persistence.EntityManager;

import static jm.task.core.jdbc.util.Util.getSessionFactoryHibernate;

public class Main {
    public static void main(String[] args) {

        // Скрывает логи hibernata
        // org.apache.log4j.BasicConfigurator.configure(new NullAppender());

//        UserDaoJDBCImpl o = new UserDaoJDBCImpl();                // JDBC
//          UserDaoHibernateImpl o = new UserDaoHibernateImpl();      // HIBERNATE
//
//        o.createUsersTable();
//
//        o.saveUser("aaa","AAA",(byte) 10);
//        o.saveUser("bbb","BBB",(byte) 20);
//        o.saveUser("ccc","CCC",(byte) 30);
//        o.saveUser("ddd","DDD",(byte) 40);
//
//        o.getAllUsers();
//        o.removeUserById(2);
//        o.getAllUsers();
//        o.cleanUsersTable();
//        o.dropUsersTable();

        ////////////////////////////////////
//
//         EntityManager ent = Util.getEntityManager();
//        ent.getTransaction().begin();
//
//                User a = new User();
//
//                a.setName("lalala");
//                a.setLastName("blblbl");
//                a.setAge((byte)5);
//
//            ent.persist(a);
//
//            ent.getTransaction().commit();

           // Util.getE();
        }



}
