package ua.nure.hrynko.SummaryTask4.db.dao.interfaces;
import ua.nure.hrynko.SummaryTask4.db.dto.Cars;
import ua.nure.hrynko.SummaryTask4.exception.DBException;
import java.util.List;


public interface CarsDAO extends AbstractDAO {

     void deleteCarToCarsDb(long id) throws DBException;


     void updateCarToCarsDb(long id, String n, int p, String c) throws DBException;


     void addCarToCarsDb(String n, int p, String c) throws DBException;


     Cars findCarToCarsDb(long id) throws DBException;


     List<Cars> findCarSortedUpByName() throws DBException;


     List<Cars> findCarSortedDownByName() throws DBException;


     List<Cars> selectCarsByCategory(String selectByClass) throws DBException;


     List<Cars> findCars() throws DBException;


   }


