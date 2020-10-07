package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.Passport;
import jm.task.core.jdbc.model.Person;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final String tableName;

    static Statement statement;
    static PreparedStatement pstatement;
    static Connection connection;

    {
       connection = Util.createconnectionJDBC();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDaoJDBCImpl(String tableName) {
        this.tableName = tableName;
    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + tableName +  "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(30), lastName varchar(30), age TINYINT)";
        try {
              statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS " + tableName;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String query = "INSERT INTO " + tableName + " (name, lastName, age) VALUES (?,?,?)";
        try {
            pstatement = connection.prepareStatement(query);
            pstatement.setString(1, name);
            pstatement.setString(2, lastName);
            pstatement.setByte  (3, age);
            pstatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                pstatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




    public void removeUserById(long id) {
//        String query =  "DELETE FROM " + tableName + " WHERE id=" + id;
        String query =  "DELETE FROM " + tableName + " WHERE id = ?";
        try {
            pstatement = connection.prepareStatement(query);
            pstatement.setLong(1, id);

            if (pstatement.executeUpdate() == 0) {
                System.out.println("Индекс " + id + " не существует");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>() ;
        String query = "SELECT * FROM " + tableName;
        ResultSet resultSet = null;
        try {
              resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id          = resultSet.getInt   (1);
                String name     = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age        = resultSet.getByte  (4);

                User user = new User(name, lastName, age);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE " + tableName;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection openConnection() {
       return Util.createconnectionJDBC();
    }

    @Override
    public void closeConnection() {
       Util.closeJDBC();
    }

    @Override
    public void savePassport(String seria, int number, Person person) {

    }
    @Override
    public void savePerson(String name, String lastName, byte age, Passport pass) {

    }

}
