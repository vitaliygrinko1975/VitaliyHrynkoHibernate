package ua.nure.hrynko.SummaryTask4.db.dao.interfaces;

import ua.nure.hrynko.SummaryTask4.db.dto.Users;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

     void addClientToUsersDb(String login, String password, String name, String lastname) throws DBException;

     void addAdminToUsersDb(String login, String password, String name, String lastname) throws DBException;

     Users findUserByLogin(String login) throws DBException;

     List<Users> findAllUsers() throws DBException;


}
