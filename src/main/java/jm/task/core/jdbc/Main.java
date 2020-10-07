package jm.task.core.jdbc;

//import jm.task.core.jdbc.dao.PersonDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.One_to_one_BiDirect.to_Passport;
import jm.task.core.jdbc.model.One_to_one_BiDirect.to_Person;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {

//        UserDao connection =  connectMeTo(ConnectorName.JDBC, TableName.ONE_to_one);
       // UserDao connection = connectMeTo(ConnectorName.HIBERNATE);


        to_Passport pass = new to_Passport();
        pass.setSeria("ABC");
        pass.setNumber(123);



        to_Person person = new to_Person();
        person.setName("BBB");
        person.setLastName("JJJ");
        person.setAge((byte)8);
        person.setPassport(pass);





       //  // переключаясь между  PersonDaoHibernateImpl и PassportsDaoHibernateImpl
        //        //можно сохранять и удалять паспорт вместе с человеком, и наоборот.

        //connection.savePerson(person);
//        connection.savePassport(pass1);


       // метод общий - надо переключать интерфейсы
        //connection.removeUserById(77);




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
        //connection.closeConnection();
    }

//    static UserDao connectMeTo(ConnectorName connectorName, String tableName) {
//        if(connectorName.equals(ConnectorName.JDBC)) {
//            return null/*new UserDaoJDBCImpl(tableName)*/;
//        } else if (connectorName.equals(ConnectorName.HIBERNATE)) {
//            throw new RuntimeException("Не нужно указывать таблицу для Hibernate.");
//        } else {throw new RuntimeException();}
//    }
//
//    static UserDao connectMeTo(ConnectorName connectorName) {
//        if(connectorName.equals(ConnectorName.JDBC)) {
//            throw new RuntimeException("Не указана таблица для JDBC");
//        } else if (connectorName.equals(ConnectorName.HIBERNATE)) {
//            return new PersonDaoHibernateImpl(TableName.HIBERNATE);
////            return new PassportsDaoHibernateImpl(TableName.HIBERNATE);
//        } else {throw new RuntimeException();}
//    }
}
