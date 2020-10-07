package jm.task.core.jdbc.model.One_to_one_BiDirect;

import jm.task.core.jdbc.ConnectorName;
import jm.task.core.jdbc.TableName;
//import jm.task.core.jdbc.dao.PassportsDaoHibernateImpl;
//import jm.task.core.jdbc.dao.PersonDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDao;
//import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class Main_One_to_One_BiDirect {

   public static Session session = Util.createSessionHIBER(to_Person.class, to_Passport.class);

    public static void main(String[] args) {

//        UserDao connection =  connectMeTo(ConnectorName.JDBC, TableName.ONE_to_one);
//        UserDao connection =  connectMeTo(ConnectorName.HIBERNATE);


        to_Passport pass1 = new to_Passport();
        pass1.setSeria("ABC");
        pass1.setNumber(123);



        to_Person person = new to_Person();
        person.setName("BBB");
        person.setLastName("JJJ");
        person.setAge((byte)8);
        person.setPassport(pass1);

        pass1.setPerson(person);







         // 1
        // СОХРАНЕНИЕ ЛЮБОГО из объектов
         saveMe(person);


        // 2
        // УДАЛЕНИЕ ПО ID
        //removeObjectById(person, 2);


        // 3
        // получание всех данны таблиц по объекту
//        getAllUsers(pass1);


        // 4 очистка таблиц - не связаны - удаляются по одной
        //cleanTable(person);
        //cleanTable(pass1);





       session.close();
       Util.closeHIBER();




        //connection.closeConnection();
    }//////////////////////////////////////////////////////////////////////////////
//..................................................................................
//      МЕТОДЫ
//..................................................................................

    public static void saveMe(Object o) {
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();

    }



    public static void cleanTable(Object o) {
        String query = "DROP TABLE " + o.getClass().getSimpleName();

        session.beginTransaction();
        session.createSQLQuery(query).executeUpdate();
        session.getTransaction().commit();
    }




    public static <T> List<T> getAllUsers(T o) {

        String query = "FROM "+ o.getClass().getSimpleName();

        session.beginTransaction();
        List<T> list = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        return list;
    }



    static void removeObjectById (Object o, long id) {

        session.beginTransaction();
        if (session.find(o.getClass(), id) != null) {
            session.delete(session.find(o.getClass(), id));
            session.getTransaction().commit();
        } else {
            System.out.println("Индекс " + id + " не существует");
        }
    }


//    static UserDao connectMeTo(ConnectorName connectorName) {
//
//            return new PersonDaoHibernateImpl(TableName.HIBERNATE);
////            return new PassportsDaoHibernateImpl(TableName.HIBERNATE);
//
//    }
}
