package ua.nure.hrynko.SummaryTask4.db.dao.interfaces;

import ua.nure.hrynko.SummaryTask4.db.dto.Users;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

     void addClientToUsersDb(String login, String password, String name, String lastname) throws DBException;

     void addManagerToUsersDb(String login, String password, String name, String lastname) throws DBException;

     void deleteUser(Integer id) throws DBException;

     Users findUser(long id) throws DBException;


     Users findUserByLogin(String login) throws DBException;

     void updateUser(Users user) throws DBException;


     void updateUser(Connection con, Users user) throws SQLException;



     List<Users> findallusers() throws DBException;



    Users extractUser(ResultSet rs) throws SQLException;

}
