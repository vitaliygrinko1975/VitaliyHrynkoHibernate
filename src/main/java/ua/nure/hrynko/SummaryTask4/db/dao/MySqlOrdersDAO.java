package ua.nure.hrynko.SummaryTask4.db.dao;
import ua.nure.hrynko.SummaryTask4.db.DBManager;
import ua.nure.hrynko.SummaryTask4.db.Fields;
import ua.nure.hrynko.SummaryTask4.db.Querys;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.OrdersDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Orders;
import ua.nure.hrynko.SummaryTask4.db.dto.Users;
import ua.nure.hrynko.SummaryTask4.exception.DBException;
import ua.nure.hrynko.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySqlOrdersDAO implements OrdersDAO {
    private static final Logger LOG = Logger.getLogger(String.valueOf(MySqlOrdersDAO.class));

    private static MySqlOrdersDAO instance;

    public static synchronized MySqlOrdersDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlOrdersDAO();
        }
        return instance;
    }
    /**
     * Returns all orders.
     *
     * @return List of order entities.
     */
    public List<Orders> findOrders() throws DBException {
        List<Orders> ordersList = new ArrayList<Orders>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_ORDERS);
            while (rs.next()) {
                ordersList.add(extractOrder(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return ordersList;
    }

    /**
     * Returns orders with the given status.
     *
     * @param statusId
     *            Status identifier.
     * @return List of order entities.
     */
    public List<Orders> findOrders(int statusId) throws DBException {
        List<Orders> ordersList = new ArrayList<Orders>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_ORDERS_BY_STATUS);
            pstmt.setInt(1, statusId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ordersList.add(extractOrder(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(
                    Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_STATUS_ID, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return ordersList;
    }

    /**
     * Returns orders with given identifiers.
     *
     * @param ids
     *            Orders identifiers.
     * @return List of order entities.
     */
    public List<Orders> findOrders(String[] ids) throws DBException {
        List<Orders> ordersList = new ArrayList<Orders>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();

            // create SQL query like "... id IN (1, 2, 7)"
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM orders WHERE id IN (");
            for (String idAsString : ids) {
                query.append(idAsString).append(',');
            }
            query.deleteCharAt(query.length() - 1);
            query.append(')');

            stmt = con.createStatement();
            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                ordersList.add(extractOrder(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(
                    Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_IDENTIFIERS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return ordersList;
    }

    /**
     * Returns orders of the given user and status
     *
     * @param user
     *            User entity.
     * @param statusId
     *            Status identifier.
     * @return List of order entities.
     * @throws DBException
     */
    public List<Orders> findOrders(Users user, int statusId) throws DBException {
        List<Orders> ordersList = new ArrayList<Orders>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_ORDERS_BY_STATUS_AND_USER);
            pstmt.setInt(1, statusId);
            pstmt.setLong(2, user.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ordersList.add(extractOrder(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(
                    Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_USER_AND_STATUS_ID, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return ordersList;
    }


    /**
     * Extracts an order entity from the result set.
     *
     * @param rs
     *            Result set from which an order entity will be extracted.
     * @return
     */
        public Orders extractOrder(ResultSet rs) throws SQLException {
        Orders orders = new Orders();
        orders.setId(rs.getLong(Fields.ENTITY_ID));
        orders.setBill(rs.getInt(Fields.ORDER_BILL));

        return orders;
    }



}
