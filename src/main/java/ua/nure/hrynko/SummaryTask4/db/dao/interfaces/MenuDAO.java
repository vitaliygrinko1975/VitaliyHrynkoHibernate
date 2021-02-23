package ua.nure.hrynko.SummaryTask4.db.dao.interfaces;

import ua.nure.hrynko.SummaryTask4.db.dto.Menu;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface MenuDAO {


     void deleteCarToMenuDb(Integer id) throws DBException;


     void updateCarToMenuDb(String id, String n, int p, String c) throws DBException;


     void addCarToMenuDb(String n, int p, String c) throws DBException;


     Menu findCarToMenuDb(long id) throws DBException;


     List<Menu> findCarSortedUpByName() throws DBException;


     List<Menu> findCarSortedDownByName() throws DBException;


     List<Menu> selectCarsByClass(String selectbyclass) throws DBException;


     List<Menu> findMenuItems() throws DBException;


    /**
     * Returns menu items with given identifiers.
     *
     * @param ids
     *            Identifiers of menu items.
     * @return List of menu item entities.
     */
     List<Menu> findMenuItems(String[] ids) throws DBException;

    /**
     * Extracts a menu item from the result set.
     *
     * @param rs
     *            Result set from which a menu item entity will be extracted.
     * @return Menu item entity.
     */
      Menu extractMenuItem(ResultSet rs) throws SQLException;


}


