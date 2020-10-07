package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.Passport;
import jm.task.core.jdbc.model.Person;
import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void savePerson(String name, String lastName, byte age, Passport pass);

    void savePassport(String seria, int number, Person person);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();

    Object openConnection();

    void closeConnection();

}
