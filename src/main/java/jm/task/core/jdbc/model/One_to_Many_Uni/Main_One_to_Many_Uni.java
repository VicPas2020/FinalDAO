package jm.task.core.jdbc.model.One_to_Many_Uni;

import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class Main_One_to_Many_Uni {

   public static Session session = Util.createSessionHIBER(PersonUni.class, to_PhoneNumbersUni.class);

    public static void main(String[] args) {


        to_PhoneNumbersUni number1 = new to_PhoneNumbersUni();
        number1.setNumber(8800201);

        to_PhoneNumbersUni number2 = new to_PhoneNumbersUni();
        number2.setNumber(8800202);

        to_PhoneNumbersUni number3 = new to_PhoneNumbersUni();
        number3.setNumber(8800203);

        PersonUni person1 = new PersonUni();
        person1.setName("FIRST");
        person1.setLastName("MAN");
        person1.setAge((byte)28);
        person1.getPhoneNumbers().add(number1);
        person1.getPhoneNumbers().add(number2);
        person1.getPhoneNumbers().add(number3);


        to_PhoneNumbersUni number11 = new to_PhoneNumbersUni();
        number11.setNumber(8800501);

        to_PhoneNumbersUni number22 = new to_PhoneNumbersUni();
        number22.setNumber(8800502);

        to_PhoneNumbersUni number33 = new to_PhoneNumbersUni();
        number33.setNumber(8800503);

        PersonUni person2 = new PersonUni();
        person2.setName("SECOND");
        person2.setLastName("FAM");
        person2.setAge((byte)28);
        person2.getPhoneNumbers().add(number11);
        person2.getPhoneNumbers().add(number22);
        person2.getPhoneNumbers().add(number33);




         // 1
        // СОХРАНЕНИЕ ЛЮБОГО из объектов
         saveMe(person1);
         saveMe(person2);


        // 2
        // УДАЛЕНИЕ ПО ID
        //removeObjectById(person1, 2);


        // 3
        // получание всех данны таблиц по объекту
//        getAllUsers(pass1);


        // 4 очистка таблиц - не связаны - удаляются по одной
        //cleanTable(person1);
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
