package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.OrdersCarsDAO;

import ua.nure.hrynko.SummaryTask4.exception.DBException;

public class MySqlOrdersCarsDAO implements OrdersCarsDAO {
    private static final Logger LOG = Logger.getLogger(MySqlCarsDAO.class);

    private static MySqlOrdersCarsDAO instance;
    public static synchronized MySqlOrdersCarsDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlOrdersCarsDAO();
        }
        return instance;
    }
   }
