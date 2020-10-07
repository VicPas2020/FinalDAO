package jm.task.core.jdbc.model.One_to_one_BiDirect;

import jm.task.core.jdbc.ConnectorName;
import jm.task.core.jdbc.TableName;
import jm.task.core.jdbc.dao.PersonDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.One_to_one_BiDirect.Passport;
import jm.task.core.jdbc.model.One_to_one_BiDirect.Person;

public class Main_One_to_One_BiDirect {
    public static void main(String[] args) {

//        UserDao connection =  connectMeTo(ConnectorName.JDBC, TableName.ONE_to_one);
        UserDao connection =  connectMeTo(ConnectorName.HIBERNATE);


        Passport pass1 = new Passport();
        pass1.setSeria("ABC");
        pass1.setNumber(123);



        Person person = new Person();
        person.setName("BBB");
        person.setLastName("JJJ");
        person.setAge((byte)8);
        person.setPassport(pass1);

        pass1.setPerson(person);



       //  // переключаясь между  PersonDaoHibernateImpl и PassportsDaoHibernateImpl
        //        //можно сохранять и удалять паспорт вместе с человеком, и наоборот.

        connection.savePerson(person);
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
        connection.closeConnection();
    }


    static UserDao connectMeTo(ConnectorName connectorName) {

            return new PersonDaoHibernateImpl(TableName.HIBERNATE);
//            return new PassportsDaoHibernateImpl(TableName.HIBERNATE);

    }
}
