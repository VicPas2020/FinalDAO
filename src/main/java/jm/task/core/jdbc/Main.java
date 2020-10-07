package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.PersonDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.Passport;
import jm.task.core.jdbc.model.Person;

public class Main {
    public static void main(String[] args) {

//        UserDao connection =  connectMeTo(ConnectorName.JDBC, TableName.ONE_to_one);
        UserDao connection =  connectMeTo(ConnectorName.HIBERNATE);


        Passport pass1 = new Passport();
        pass1.setSeria("ABC");
        pass1.setNumber(123);



//        Person pers1 = new Person();
//        pers1.setName("BBB");
//        pers1.setLastName("JJJ");
//        pers1.setAge((byte)8);
//        pers1.setPassport(pass1);

        connection.savePerson("a", "A", (byte)1, pass1);

        //connection.removeUserById(31);












        /*connection.createUsersTable();

        connection.saveUser("aaa","AAA",(byte) 23);
        connection.saveUser("bbb","BBB",(byte) 23);
        connection.saveUser("ccc","CCC",(byte) 23);

        connection.removeUserById(1);

        connection.saveUser("ddd","DDD",(byte) 23);
        connection.saveUser("eee","EEE",(byte) 23);
        connection.saveUser("fff","FFF",(byte) 23);

        connection.getAllUsers();
        connection.removeUserById(5);
        //connection.cleanUsersTable();
//        connection.dropUsersTable();*/
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
            return new PersonDaoHibernateImpl(TableName.HIBERNATE);
        } else {throw new RuntimeException();}
    }
}
