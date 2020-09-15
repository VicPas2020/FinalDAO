package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
//    UserDaoJDBCImpl in = new UserDaoJDBCImpl();
        UserDaoHibernateImpl in = new UserDaoHibernateImpl();

    public void createUsersTable() {
        //UserDaoJDBCImpl in = new UserDaoJDBCImpl();
        in.createUsersTable();


    }

    public void dropUsersTable() {
        //UserDaoJDBCImpl in = new UserDaoJDBCImpl();
        in.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        //UserDaoJDBCImpl in = new UserDaoJDBCImpl();
        in.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        //UserDaoJDBCImpl in = new UserDaoJDBCImpl();
        in.removeUserById(id);
    }

    public List<User> getAllUsers() {
        //UserDaoJDBCImpl in = new UserDaoJDBCImpl();
        return in.getAllUsers();
    }

    public void cleanUsersTable() {
        //UserDaoJDBCImpl in = new UserDaoJDBCImpl();
        in.cleanUsersTable();
    }
}
