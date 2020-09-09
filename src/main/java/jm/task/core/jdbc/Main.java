package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {

        UserDaoJDBCImpl o = new UserDaoJDBCImpl();

        o.createUsersTable();

        o.saveUser("aaa","AAA",(byte) 10);
        o.saveUser("bbb","BBB",(byte) 20);
        o.saveUser("ccc","CCC",(byte) 30);
        o.saveUser("ddd","DDD",(byte) 40);

        System.out.println(o.getAllUsers());

        o.cleanUsersTable();
        o.dropUsersTable();
    }
}
