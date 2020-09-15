package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {

        // Скрывает логи hibernata
        // org.apache.log4j.BasicConfigurator.configure(new NullAppender());

//        UserDaoJDBCImpl o = new UserDaoJDBCImpl();                // JDBC
          UserDaoHibernateImpl o = new UserDaoHibernateImpl();      // HIBERNATE

        o.createUsersTable();

        o.saveUser("aaa","AAA",(byte) 10);
        o.saveUser("bbb","BBB",(byte) 20);
        o.saveUser("ccc","CCC",(byte) 30);
        o.saveUser("ddd","DDD",(byte) 40);

        o.getAllUsers();
        o.removeUserById(2);
        o.getAllUsers();
        o.cleanUsersTable();
        o.dropUsersTable();

    }
}
