package ua.nure.hrynko.SummaryTask4.db.dao.interfaces;

import ua.nure.hrynko.SummaryTask4.db.models.Users;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import java.util.List;

public interface UserDAO {

     void addClientToUsersDb(String login, String password, String name, String lastname) throws DBException;

     void addAdminToUsersDb(String login, String password, String name, String lastname) throws DBException;

     Users findUserByLogin(String login) throws DBException;

     List<Users> findAllUsers() throws DBException;


}
