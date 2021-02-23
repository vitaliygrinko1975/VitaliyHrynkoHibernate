package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.db.DBManager;
import ua.nure.hrynko.SummaryTask4.db.Fields;
import ua.nure.hrynko.SummaryTask4.db.Query;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.OrderMenuDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Menu;
import ua.nure.hrynko.SummaryTask4.db.dto.Orders;
import ua.nure.hrynko.SummaryTask4.exception.DBException;
import ua.nure.hrynko.SummaryTask4.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlOrderMenuDAO  implements OrderMenuDAO {
    private static final Logger LOG = Logger.getLogger(MySqlMenuDAO.class);

    private static MySqlOrderMenuDAO instance;
    public static synchronized MySqlOrderMenuDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlOrderMenuDAO();
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
    public List<Menu> findMenuItems(Orders orders) throws DBException {
        List<Menu> menuItemsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(Query.SQL_FIND_MENU_ITEMS_BY_ORDER);
            pstmt.setLong(1, orders.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                menuItemsList.add(extractMenuItem(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_ORDER, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_ORDER, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return menuItemsList;
    }
    public Menu extractMenuItem(ResultSet rs) throws SQLException {
        Menu menuItem = new Menu();
        menuItem.setId(rs.getLong(Fields.ENTITY_ID));
        menuItem.setName(rs.getString(Fields.MENU_ITEM_NAME));
        menuItem.setPrice(rs.getInt(Fields.MENU_ITEM_PRICE));
        menuItem.setCategory(rs.getString(Fields.MENU_ITEM_CATEGORY));
        return menuItem;
    }


}
