package ua.nure.hrynko.SummaryTask4.db.dao.interfaces;

import ua.nure.hrynko.SummaryTask4.db.dto.User;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

     void addClientToUsersDb(String login, String password, String name, String lastname) throws DBException;

     void addManagerToUsersDb(String login, String password, String name, String lastname) throws DBException;

     void deleteUser(Integer id) throws DBException;

     User findUser(long id) throws DBException;


     User findUserByLogin(String login) throws DBException;

     void updateUser(User user) throws DBException;


     void updateUser(Connection con, User user) throws SQLException;



     List<User> findallusers() throws DBException;



    User extractUser(ResultSet rs) throws SQLException;

}
