package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoJDBCImpl implements UserDao {

    private final String table_name = "new_table3";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + table_name +
                " (id INT NOT NULL, " +
                "name VARCHAR(20), " +
                "lastName VARCHAR(20) NOT NULL, " +
                "age TINYINT NOT NULL DEFAULT 0, PRIMARY KEY (id) )";
        connectUpdate(query);
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS " + table_name;
        connectUpdate(query);
    }

    public void saveUser(String name, String lastName, byte age) {
        String l = "'";
        String ll = ", ";

        String query =  "INSERT INTO " +table_name +
                " VALUES ("+
                (counter() +1) + ll  +
                l + name       + l   + ll +
                l + lastName   + l   + ll +
                age + ")";
        connectUpdate(query);
        System.out.println("User с именем – "+name+" добавлен в базу данных");
    }

    public void removeUserById(long id) {
        String query =  "DELETE FROM " +table_name + " WHERE id=" + id;
        connectUpdate(query);
    }

    public List<User> getAllUsers() {
        List<User> list ;
        String query = "SELECT * FROM "+table_name;
        list = connectSelect(query);
        System.out.println(list);
        return list;
    }

    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE " + table_name;
        connectUpdate(query);
    }
}
