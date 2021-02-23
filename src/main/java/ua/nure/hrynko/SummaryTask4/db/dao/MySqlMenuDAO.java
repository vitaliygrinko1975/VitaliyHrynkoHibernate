package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.db.DBManager;
import ua.nure.hrynko.SummaryTask4.db.Fields;
import ua.nure.hrynko.SummaryTask4.db.Query;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.MenuDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Menu;
import ua.nure.hrynko.SummaryTask4.exception.DBException;
import ua.nure.hrynko.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlMenuDAO implements MenuDAO {
    private static final Logger LOG = Logger.getLogger(MySqlMenuDAO.class);

    private static MySqlMenuDAO instance;
    public static synchronized MySqlMenuDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlMenuDAO();
        }
        return instance;
    }


    public void deleteCarToMenuDb(Integer id) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.prepareStatement(
                    "DELETE FROM menu WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
        }
        finally {
            DBManager.close(con, stmt, rs);
        }

    }

    public void updateCarToMenuDb(String id, String n, int p, String c) throws DBException {
        PreparedStatement  stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.prepareStatement("UPDATE menu SET name=?, price=?, category=?"
                    + "	WHERE id=? ");
            stmt.setString(1, n);
            stmt.setInt(2, p);
            stmt.setString(3, c);
            stmt.setString(4, id);
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

    }


    public void addCarToMenuDb(String n, int p, String c) throws DBException {
        PreparedStatement  stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.prepareStatement("INSERT INTO menu (name, price, category)  VALUE (?,?,?)");
            stmt.setString(1, n);
            stmt.setInt(2, p);
            stmt.setString(3, c);
            stmt.executeUpdate();
            con.commit();
            LOG.trace("add to SQL seccesful--> " );
        } catch (SQLException ex) {
            LOG.trace("ERRor--> " );
            ex.printStackTrace();
            DBManager.rollback(con);
        }
        finally {
            DBManager.close(con, stmt, rs);
        }

    }


    public Menu findCarToMenuDb(long id) throws DBException {
        Menu Item = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement("SELECT * FROM menu WHERE id=?");
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Item = extractMenuItem(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return Item;
    }
    public List<Menu> findCarSortedUpByName() throws DBException {
        List<Menu> carSortedUp = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM menu ORDER BY name ASC ");
            while (rs.next()) {
                carSortedUp.add(extractMenuItem(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return carSortedUp;
    }

    public List<Menu> findCarSortedDownByName() throws DBException {
        List<Menu> carsorteddown = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM menu ORDER BY name DESC ");
            while (rs.next()) {
                carsorteddown.add(extractMenuItem(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return carsorteddown;
    }
    public List<Menu> selectCarsByClass(String selectbyclass) throws DBException {
        List<Menu> carsItemsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement("SELECT * FROM menu WHERE category=?");
            pstmt.setString(1, selectbyclass);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                carsItemsList.add(extractMenuItem(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return carsItemsList;
    }

    public boolean thereIsSuchClass(String selecterByClass) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean rez = false;
        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement("SELECT * FROM menu WHERE category=? ");
            pstmt.setString(1, selecterByClass);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                rez = true;
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        } return rez;
    }

    public List<Menu> findMenuItems() throws DBException {
        List<Menu> menuItemsList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Query.SQL_FIND_ALL_MENU_ITEMS);
            while (rs.next()) {
                menuItemsList.add(extractMenuItem(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return menuItemsList;
    }


    /**
     * Returns menu items with given identifiers.
     *
     * @param ids
     *            Identifiers of menu items.
     * @return List of menu item entities.
     */
    public List<Menu> findMenuItems(String[] ids) throws DBException {
        List<Menu> menuItemsList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();

            // create SQL query like "... id IN (1, 2, 7)"
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM menu WHERE id IN (");
            for (String idAsString : ids) {
                query.append(idAsString).append(',');
            }
            query.deleteCharAt(query.length() - 1);
            query.append(')');

            stmt = con.createStatement();
            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                menuItemsList.add(extractMenuItem(rs));
            }
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(
                    Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_IDENTIFIERS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return menuItemsList;
    }


    /**
     * Extracts a menu item from the result set.
     *
     * @param rs
     *            Result set from which a menu item entity will be extracted.
     * @return Menu item entity.
     */
    public Menu extractMenuItem(ResultSet rs) throws SQLException {
        Menu menuItem = new Menu();
        menuItem.setId(rs.getLong(Fields.ENTITY_ID));
        menuItem.setName(rs.getString(Fields.MENU_ITEM_NAME));
        menuItem.setPrice(rs.getInt(Fields.MENU_ITEM_PRICE));
        menuItem.setCategory(rs.getString(Fields.MENU_ITEM_CATEGORY));
        return menuItem;
    }



}
