package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.db.DBManager;
import ua.nure.hrynko.SummaryTask4.db.Fields;
import ua.nure.hrynko.SummaryTask4.db.Querys;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.OrdersCarsDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Cars;
import ua.nure.hrynko.SummaryTask4.db.dto.Orders;
import ua.nure.hrynko.SummaryTask4.exception.DBException;
import ua.nure.hrynko.SummaryTask4.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlOrdersCarsDAO implements OrdersCarsDAO {
    private static final Logger LOG = Logger.getLogger(MySqlCarsDAO.class);

    private static MySqlOrdersCarsDAO instance;
    public static synchronized MySqlOrdersCarsDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlOrdersCarsDAO();
        }
        return instance;
    }

    /**
     * Returns menu items of the given orders.
     *
     * @param orders
     *            Orders entity.
     * @return List of menu item entities.
     */
    public List<Cars> findCarsItems(Orders orders) throws DBException {
        List<Cars> carsIsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_MENU_ITEMS_BY_ORDER);
            pstmt.setLong(1, orders.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                carsIsList.add(extractCarsItem(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_ORDER, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_ORDER, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return carsIsList;
    }
    public Cars extractCarsItem(ResultSet rs) throws SQLException {
        Cars carsItems = new Cars();
        carsItems.setId(rs.getLong(Fields.ENTITY_ID));
        carsItems.setName(rs.getString(Fields.CARS_ITEM_NAME));
        carsItems.setPrice(rs.getInt(Fields.CARS_ITEM_PRICE));
        carsItems.setCategory(rs.getString(Fields.CARS_ITEM_CATEGORY));
        return carsItems;
    }


}
