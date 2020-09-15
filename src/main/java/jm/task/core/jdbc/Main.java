package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class Main {
    public static void main(String[] args) {

       // org.apache.log4j.BasicConfigurator.configure(new NullAppender());
        /*UserDaoJDBCImpl o = new UserDaoJDBCImpl();

        o.createUsersTable();

        o.saveUser("aaa","AAA",(byte) 10);
        o.saveUser("bbb","BBB",(byte) 20);
        o.saveUser("ccc","CCC",(byte) 30);
        o.saveUser("ddd","DDD",(byte) 40);

        System.out.println(o.getAllUsers());

        o.cleanUsersTable();
        o.dropUsersTable();*/

        //UserDaoHibernateImpl o = new UserDaoHibernateImpl();
        //o.getAllUsers();
        //o.saveUser("Porshe", "eeee", (byte) 25);//  o.removeUserById(0);


    }
}
