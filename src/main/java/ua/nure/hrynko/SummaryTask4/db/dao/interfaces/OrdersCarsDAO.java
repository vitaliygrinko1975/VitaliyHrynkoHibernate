package ua.nure.hrynko.SummaryTask4.db.dao.interfaces;

import ua.nure.hrynko.SummaryTask4.db.dto.Cars;
import ua.nure.hrynko.SummaryTask4.db.dto.Orders;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrdersCarsDAO {
    /**
     * Returns menu items of the given orders.
     *
     * @param orders
     *            Orders entity.
     * @return List of menu item entities.
     */
     List<Cars> findCarsItems(Orders orders) throws DBException;

     Cars extractCarsItem(ResultSet rs) throws SQLException;

}
