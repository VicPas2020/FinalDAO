package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {

        UserDao connection =  connectMeTo(ConnectorName.JDBC, TableName.TABLE_4);

        connection.createUsersTable();

        connection.saveUser("sss","AAA",(byte) 23);
        connection.saveUser("fff","AAA",(byte) 23);
        connection.saveUser("www","AAA",(byte) 23);
        connection.removeUserById(1);
        connection.saveUser("www","AAA",(byte) 23);
        connection.saveUser("www","AAA",(byte) 23);
        connection.saveUser("www","AAA",(byte) 23);
        connection.getAllUsers();
        connection.removeUserById(5);
        //connection.cleanUsersTable();
//        connection.dropUsersTable();
        connection.closeConnection();
    }

    static UserDao connectMeTo(ConnectorName connectorName, String tableName) {
        if(connectorName.equals(ConnectorName.JDBC)) {
            return new UserDaoJDBCImpl(tableName);
        } else if (connectorName.equals(ConnectorName.HIBERNATE)) {
            throw new RuntimeException("Не нужно указывать таблицу для Hibernate.");
        } else {throw new RuntimeException();}
    }

    static UserDao connectMeTo(ConnectorName connectorName) {
        if(connectorName.equals(ConnectorName.JDBC)) {
            throw new RuntimeException("Не указана таблица для JDBC");
        } else if (connectorName.equals(ConnectorName.HIBERNATE)) {
            return new UserDaoHibernateImpl(TableName.HIBERNATE);
        } else {throw new RuntimeException();}
    }
}
