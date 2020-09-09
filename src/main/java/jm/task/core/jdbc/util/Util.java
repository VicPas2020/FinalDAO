package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    // реализуйте настройку соеденения с БД
// JDBC URL, username and password of MySQL server
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
     * Нужен для того, чтобы знатьпоследний порядковый номер объекта в базе данных.
     *
     * @return номер последнего объекта в таблице
     */
    public static int counter() {
        int res=-1;
        String s = "SELECT MAX(id) AS FFF FROM new_table3";
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


}
