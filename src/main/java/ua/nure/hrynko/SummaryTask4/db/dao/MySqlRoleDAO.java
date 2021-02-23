package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.RoleDAO;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

public class MySqlRoleDAO implements RoleDAO {
    private static final Logger LOG = Logger.getLogger(MySqlRoleDAO.class);

    private static MySqlRoleDAO instance;

    public static synchronized MySqlRoleDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlRoleDAO();
        }
        return instance;
    }




}
