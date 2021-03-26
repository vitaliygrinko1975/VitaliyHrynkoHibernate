package ua.nure.hrynko.SummaryTask4.db.dao.interfaces;

import ua.nure.hrynko.SummaryTask4.db.dto.Cars;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//
public interface CarsDAO extends AbstractDAO {


     void deleteCarToCarsDb(Integer id) throws DBException;


//     void updateCarToCarsDb(String id, String n, int p, String c) throws DBException;
//
//
//     void addCarToCarsDb(String n, int p, String c) throws DBException;
//
//
//     Cars findCarToCarsDb(long id) throws DBException;
//
//
//     List<Cars> findCarSortedUpByName() throws DBException;
//
//
//     List<Cars> findCarSortedDownByName() throws DBException;
//
//
//     List<Cars> selectCarsByClass(String selectByClass) throws DBException;
//
//
//     List<Cars> findCars() throws DBException;
//
//
//    /**
//     * Returns menu items with given identifiers.
//     *
//     * @param ids
//     *            Identifiers of menu items.
//     * @return List of menu item entities.
//     */
//    public List<Cars> findCars(String[] ids) throws DBException;
//
//    /**
//     * Extracts a menu item from the result set.
//     *
//     * @param rs
//     *            Result set from which a menu item entity will be extracted.
//     * @return Cars item entity.
//     */
//      Cars extractCarsItem(ResultSet rs) throws SQLException;
//

}


