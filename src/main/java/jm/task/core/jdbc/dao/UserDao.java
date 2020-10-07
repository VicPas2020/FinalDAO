//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.One_to_one_BiDirect.to_Passport;
//import jm.task.core.jdbc.model.One_to_one_BiDirect.to_Person;
//import jm.task.core.jdbc.model.User;
//
//import java.util.List;
//
//public interface UserDao {
//    void createUsersTable();
//
//    void dropUsersTable();
//
//    void saveUser(String name, String lastName, byte age);
//
//    void savePerson(to_Person person);
//
//    void savePassport(to_Passport passport);
//
//    void removeUserById(long id);
//
//    List<User> getAllUsers();
//
//    void cleanUsersTable();
//
//    Object openConnection();
//
//    void closeConnection();
//
//}
