package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.db.DBManager;
import ua.nure.hrynko.SummaryTask4.db.Fields;
import ua.nure.hrynko.SummaryTask4.db.Query;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.UserDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.User;
import ua.nure.hrynko.SummaryTask4.exception.DBException;
import ua.nure.hrynko.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.hrynko.SummaryTask4.db.DBManager.getConnection;

public class MySqlUserDAO implements UserDAO {
    private static final Logger LOG = Logger.getLogger(MySqlMenuDAO.class);

    private static MySqlUserDAO instance;

    public static synchronized MySqlUserDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlUserDAO();
        }
        return instance;
    }
    public void addClientToUsersDb(String login, String password, String name, String lastname) throws DBException {
        PreparedStatement  stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement("INSERT INTO users (login, password, first_name,last_name,role_id)  VALUE (?,?,?,?,1)");
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, lastname);
            stmt.executeUpdate();
            con.commit();
            LOG.trace("add user to SQL succesful--> " );
        } catch (SQLException ex) {
            LOG.trace("ERRor--> " );
            ex.printStackTrace();
            DBManager.rollback(con);
        }
        finally {
            DBManager.close(con, stmt, rs);
        }

    }


    public void addManagerToUsersDb(String login, String password, String name, String lastname) throws DBException {
        PreparedStatement  stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement("INSERT INTO users (login, password, first_name,last_name,role_id)  VALUE (?,?,?,?,2)");
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, lastname);
            stmt.executeUpdate();
            con.commit();
            LOG.trace("add manager to SQL succesful--> " );
        } catch (SQLException ex) {
            LOG.trace("ERRor--> " );
            ex.printStackTrace();
            DBManager.rollback(con);
        }
        finally {
            DBManager.close(con, stmt, rs);
        }

    }


    public void deleteUser(Integer id) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(
                    "DELETE FROM users WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.commit();
            LOG.trace("delete manager to SQL succesful--> " );
        } catch (SQLException ex) {
            LOG.trace("ERRor--> " );
            ex.printStackTrace();
            DBManager.rollback(con);
        }
        finally {
            DBManager.close(con, stmt, rs);
        }

    }
    public User findUser(long id) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(Query.SQL_FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return user;
    }



    public User findUserByLogin(String login) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(Query.SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return user;
    }


    public void updateUser(User user) throws DBException {
        Connection con = null;
        try {
            con = getConnection();
            updateUser(con, user);
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
        } finally {
            DBManager.close(con);
        }
    }

    // //////////////////////////////////////////////////////////
    // Entity access methods (for transactions)
    // //////////////////////////////////////////////////////////


    public void updateUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(Query.SQL_UPDATE_USER);
            int k = 1;
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setLong(k, user.getId());
            pstmt.executeUpdate();
        } finally {
            DBManager.close(pstmt);
        }
    }

    public List<User> findallusers() throws DBException {
        List<User> alluserList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users ");//ORDER BY lastName ASC
            while (rs.next()) {
                alluserList.add(extractUser(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all user", ex);
            throw new DBException("cannot select all user", ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return alluserList;
    }




    /**
     * Extracts a user entity from the result set.
     *
     * @param rs
     *            Result set from which a user entity will be extracted.
     * @return User entity
     */
        public User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        return user;
    }












}