package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.db.DBManager;
import ua.nure.hrynko.SummaryTask4.db.Fields;
import ua.nure.hrynko.SummaryTask4.db.Query;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.StatuseDAO;
import ua.nure.hrynko.SummaryTask4.db.UserOrderBean;
import ua.nure.hrynko.SummaryTask4.exception.DBException;
import ua.nure.hrynko.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlStatuseDAO implements StatuseDAO {
    private static final Logger LOG = Logger.getLogger(MySqlMenuDAO.class);

    private static MySqlStatuseDAO instance;

    public static synchronized MySqlStatuseDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlStatuseDAO();
        }
        return instance;
    }

    public Object updateByIdToStatusDbOnBlocking(String id) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.prepareStatement("UPDATE statuses SET name=BLOCKING"
                    + "	WHERE id=? ");
            stmt.setString(1, id);
            stmt.executeUpdate();
            con.commit();
            LOG.trace("update to SQL seccesful--> " );
        } catch (SQLException ex) {
            LOG.trace("ERRor--> " );
            ex.printStackTrace();
            DBManager.rollback(con);
        }
        finally {
            DBManager.close(con, stmt, rs);
        }

        return null;
    }

    public Object updateByIdToStatusDbOnUnBlocking(String id) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.prepareStatement("UPDATE statuses SET name=UNBLOCKING"
                    + "	WHERE id=? ");
            stmt.setString(1, id);
            stmt.executeUpdate();
            con.commit();
            LOG.trace("update to SQL seccesful--> " );
        } catch (SQLException ex) {
            LOG.trace("ERRor--> " );
            ex.printStackTrace();
            DBManager.rollback(con);
        }
        finally {
            DBManager.close(con, stmt, rs);
        }

        return null;
    }



    /**
     * Returns all categories.
     *
     * @return List of category entities.
     */
    public List<UserOrderBean> getUserOrderBeans() throws DBException {
        List<UserOrderBean> orderUserBeanList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Query.SQL_GET_USER_ORDER_BEANS);
            while (rs.next()) {
                orderUserBeanList.add(extractUserOrderBean(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_ORDER_BEANS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_ORDER_BEANS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return orderUserBeanList;
    }

    /**
     * Extracts a user order bean from the result set.
     *
     * @param rs
     *            Result set from which a user order bean will be extracted.
     * @return UserOrderBean object
     */

    public UserOrderBean extractUserOrderBean(ResultSet rs)
            throws SQLException {
        UserOrderBean bean = new UserOrderBean();
        bean.setId(rs.getLong(Fields.USER_ORDER_BEAN_ORDER_ID));
        bean.setOrderBill(rs.getInt(Fields.USER_ORDER_BEAN_ORDER_BILL));
        bean.setUserFirstName(rs
                .getString(Fields.USER_ORDER_BEAN_USER_FIRST_NAME));
        bean.setUserLastName(rs
                .getString(Fields.USER_ORDER_BEAN_USER_LAST_NAME));
        bean.setStatusName(rs.getString(Fields.USER_ORDER_BEAN_STATUS_NAME));
        return bean;
    }



}
