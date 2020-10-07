package jm.task.core.jdbc.model.One_to_One_UniDirect;

import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class Main_One_to_One_UniDirect {

   public static Session session = Util.createSessionHIBER(Person.class, to_Passport.class);

    public static void main(String[] args) {


        to_Passport pass1 = new to_Passport();
        pass1.setSeria("DEF");
        pass1.setNumber(456);



        Person person = new Person();
        person.setName("OOO");
        person.setLastName("KKK");
        person.setAge((byte)18);
        person.setPassport(pass1);
        //pass1.setPerson(person);



         // 1
        // СОХРАНЕНИЕ ЛЮБОГО из объектов = НО ТОЛЬКО ОДНОГО -

         saveMe(person);


        //TODO: почему не пишет несколько подряд saveMe(person);
        // saveMe(person); saveMe(person); saveMe(person);


        // 2
        // УДАЛЕНИЕ ПО ID
        //removeObjectById(pass1, 2);


        // 3
        // получание всех данны таблиц по объекту
        //getAllUsers(person);


        // 4 очистка таблиц - не связаны - удаляются по одной
        //cleanTable(person);
        //cleanTable(pass1);





       session.close();
       Util.closeHIBER();




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
