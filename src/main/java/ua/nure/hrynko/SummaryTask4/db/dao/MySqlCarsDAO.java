package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.nure.hrynko.SummaryTask4.db.DBManager;
import ua.nure.hrynko.SummaryTask4.db.Fields;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.CarsDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Cars;
import ua.nure.hrynko.SummaryTask4.exception.DBException;
import ua.nure.hrynko.SummaryTask4.exception.Messages;

import javax.persistence.Entity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//extends MySqlAbstractDAO implements CarsDAO
public class MySqlCarsDAO  {

    private static final Logger LOG = Logger.getLogger(MySqlCarsDAO.class);

//    public MySqlCarsDAO() {
//        super(Cars.class, Cars.class.getAnnotation(Entity.class).name());
//    }


    private static MySqlCarsDAO instance;

    public static synchronized MySqlCarsDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlCarsDAO();
        }
        return instance;
    }


    public void deleteCarToCarsDb(Integer id) throws DBException {

//        Cars car = sessionFactory.getCurrentSession().get(Cars.class, id);
//        sessionFactory.getCurrentSession().delete(car);


        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.prepareStatement(
                    "DELETE FROM cars WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, stmt, rs);
        }

    }

        public void updateCarToCarsDb(String id, String name, int price, String category) throws DBException {
            PreparedStatement  stmt = null;
            ResultSet rs = null;
            Connection con = null;
            try {
                con = DBManager.getConnection();
                stmt = con.prepareStatement("UPDATE cars SET name=?, price=?, category=?"
                        + "	WHERE id=? ");
                stmt.setString(1, name);
                stmt.setInt(2, price);
                stmt.setString(3, category);
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


        public void addCarToCarsDb(String name, int price, String category) throws DBException {
            PreparedStatement  stmt = null;
            ResultSet rs = null;
            Connection con = null;
            try {
                con = DBManager.getConnection();
                stmt = con.prepareStatement("INSERT INTO cars (name, price, category)  VALUE (?,?,?)");
                stmt.setString(1, name);
                stmt.setInt(2, price);
                stmt.setString(3, category);
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


       // public Cars findCarToCarsDb(long id) throws DBException {


    public List<Cars> selectCarsByClass(String selectbyclass) throws DBException {
        List<Cars> carsItemsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement("SELECT * FROM cars WHERE category=?");
            pstmt.setString(1, selectbyclass);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                carsItemsList.add(extractCarsItem(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return carsItemsList;
    }

    public boolean thereIsSuchCategory(String selecterByClass) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean rez = false;
        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement("SELECT * FROM cars WHERE category=? ");
            pstmt.setString(1, selecterByClass);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                rez = true;
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return rez;
    }

    public List<Cars> findCars() throws DBException {
//        List<Cars> carsList = new ArrayList<>();
//        carsList = getAll();
//        return carsList;

            List<Cars> carsList = new ArrayList<>();
            Statement stmt = null;
            ResultSet rs = null;
            Connection con = null;
            try {
                con = DBManager.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM cars");
                while (rs.next()) {
                    carsList.add(extractCarsItem(rs));
                }
                con.commit();
            } catch (SQLException ex) {
                DBManager.rollback(con);
                LOG.error(Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS, ex);
                throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS, ex);
            } finally {
                DBManager.close(con, stmt, rs);
            }
              return carsList;
}

    public List<Cars> findCarSortedUpByName() throws DBException {
        List<Cars> carSortedUp = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM cars ORDER BY name ASC ");
            while (rs.next()) {
                carSortedUp.add(extractCarsItem(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return carSortedUp;
    }
    public List<Cars> findCarSortedDownByName() throws DBException {
        List<Cars> carsorteddown = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM cars ORDER BY name DESC ");
            while (rs.next()) {
                carsorteddown.add(extractCarsItem(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return carsorteddown;
    }
    //
//
//        /**
//         * Returns cars items with given identifiers.
//         *
//         * @param ids
//         *            Identifiers of cars items.
//         * @return List of cars item entities.
//         */
//        public List<Cars> findCars(String[] ids) throws DBException {
//            List<Cars> carsList = new ArrayList<>();
//            Statement stmt = null;
//            ResultSet rs = null;
//            Connection con = null;
//            try {
//                con = DBManager.getConnection();
//
//                // create SQL query like "... id IN (1, 2, 7)"
//                StringBuilder query = new StringBuilder(
//                        "SELECT * FROM cars WHERE id IN (");
//                for (String idAsString : ids) {
//                    query.append(idAsString).append(',');
//                }
//                query.deleteCharAt(query.length() - 1);
//                query.append(')');
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query.toString());
//                while (rs.next()) {
//                    carsList.add(extractCarsItem(rs));
//                }
//            } catch (SQLException ex) {
//                DBManager.rollback(con);
//                throw new DBException(
//                        Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS_BY_IDENTIFIERS, ex);
//            } finally {
//                DBManager.close(con, stmt, rs);
//            }
//            return carsList;
//        }
//
//
//        /**
//         * Extracts a menu item from the result set.
//         *
//         * @param rs
//         *            Result set from which a menu item entity will be extracted.
//         * @return Menu item entity.
//         */
    public Cars extractCarsItem(ResultSet rs) throws SQLException {
        Cars cars = new Cars();
        cars.setId(rs.getLong(Fields.ENTITY_ID));
        cars.setName(rs.getString(Fields.CARS_ITEM_NAME));
        cars.setPrice(rs.getInt(Fields.CARS_ITEM_PRICE));
        cars.setCategory(rs.getString(Fields.CARS_ITEM_CATEGORY));
        return cars;
    }

}


