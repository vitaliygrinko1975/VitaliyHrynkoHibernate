package ua.nure.hrynko.SummaryTask4.db.dao.interfaces;

import ua.nure.hrynko.SummaryTask4.db.dto.Orders;
import ua.nure.hrynko.SummaryTask4.db.dto.User;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface OrderDAO {

        /**
     * Returns all orders.
     *
     * @return List of order entities.
     */
    List<Orders> findOrders() throws DBException;

    /**
     * Returns orders with the given status.
     *
     * @param statusId
     *            Status identifier.
     * @return List of order entities.
     */
        List<Orders> findOrders(int statusId) throws DBException;
    /**
     * Returns orders with given identifiers.
     *
     * @param ids
     *            Orders identifiers.
     * @return List of order entities.
     */
    List<Orders> findOrders(String[] ids) throws DBException;

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
    public List<Orders> findOrders(User user, int statusId) throws DBException;
    /**
     * Extracts an order entity from the result set.
     *
     * @param rs
     *            Result set from which an order entity will be extracted.
     * @return
     */
    Orders extractOrder(ResultSet rs) throws SQLException;
}
