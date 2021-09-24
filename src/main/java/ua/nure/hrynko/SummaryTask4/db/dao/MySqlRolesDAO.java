package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.RolesDAO;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

public class MySqlRolesDAO implements RolesDAO {
    private static final Logger LOG = Logger.getLogger(MySqlRolesDAO.class);

    private static MySqlRolesDAO instance;

    public static synchronized MySqlRolesDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlRolesDAO();
        }
        return instance;
    }
}