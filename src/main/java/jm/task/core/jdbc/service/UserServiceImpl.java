package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    // два варианта
//    UserDaoJDBCImpl in = new UserDaoJDBCImpl();
  UserDaoHibernateImpl in = new UserDaoHibernateImpl();

    public void createUsersTable() {
        in.createUsersTable();
    }

    public void dropUsersTable() {
        in.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        in.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        in.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return in.getAllUsers();
    }

    public void cleanUsersTable() {
        in.cleanUsersTable();
    }
}
