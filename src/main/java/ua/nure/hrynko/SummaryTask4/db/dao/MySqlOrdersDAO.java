package ua.nure.hrynko.SummaryTask4.db.dao;

import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.OrdersDAO;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import java.util.logging.Logger;

public class MySqlOrdersDAO implements OrdersDAO {
    private static final Logger LOG = Logger.getLogger(String.valueOf(MySqlOrdersDAO.class));

    private static MySqlOrdersDAO instance;

    public static synchronized MySqlOrdersDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlOrdersDAO();
        }
        return instance;
    }


}
