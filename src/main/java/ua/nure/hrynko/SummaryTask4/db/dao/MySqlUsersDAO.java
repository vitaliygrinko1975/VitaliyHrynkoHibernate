package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.db.DBManager;
import ua.nure.hrynko.SummaryTask4.db.Fields;
import ua.nure.hrynko.SummaryTask4.db.Querys;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.UserDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Roles;
import ua.nure.hrynko.SummaryTask4.db.dto.Users;
import ua.nure.hrynko.SummaryTask4.exception.DBException;
import ua.nure.hrynko.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.hrynko.SummaryTask4.db.DBManager.getConnection;

public class MySqlUsersDAO implements UserDAO {
    private static final Logger LOG = Logger.getLogger(MySqlUsersDAO.class);

    private static MySqlUsersDAO instance;

    public static synchronized MySqlUsersDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlUsersDAO();
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
    public Users findUser(long id) throws DBException {
        Users user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_USER_BY_ID);
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



    public Users findUserByLogin(String login) throws DBException {
        Users user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_USER_BY_LOGIN);
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


    public void updateUser(Users user) throws DBException {
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
    // Entityes access methods (for transactions)
    // //////////////////////////////////////////////////////////


    public void updateUser(Connection con, Users user) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(Querys.SQL_UPDATE_USER);
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

    public List<Users> findallusers() throws DBException {
        List<Users> alluserList = new ArrayList<>();
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
        public Users extractUser(ResultSet rs) throws SQLException {
            Users user = new Users();
            user.setId(rs.getLong(Fields.ENTITY_ID));
            user.setLogin(rs.getString(Fields.USER_LOGIN));
            user.setPassword(rs.getString(Fields.USER_PASSWORD));
            user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
            user.setLastName(rs.getString(Fields.USER_LAST_NAME));
            Long roleId = rs.getLong(Fields.USER_ROLE_ID);
            Roles role = MySqlRolesDAO.getInstance().findRoleById(roleId);
            user.setRole(role);
            return user;
    }












}