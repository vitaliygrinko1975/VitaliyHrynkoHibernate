package ua.nure.hrynko.SummaryTask4.db.dao.interfaces;

import ua.nure.hrynko.SummaryTask4.db.dto.Menu;
import ua.nure.hrynko.SummaryTask4.db.dto.Orders;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderMenuDAO {
    /**
     * Returns menu items of the given orders.
     *
     * @param orders
     *            Orders entity.
     * @return List of menu item entities.
     */
     List<Menu> findMenuItems(Orders orders) throws DBException;

     Menu extractMenuItem(ResultSet rs) throws SQLException;

}
