package ua.nure.hrynko.SummaryTask4.db.dao.interfaces;

import ua.nure.hrynko.SummaryTask4.exception.DBException;

public interface StatuseDAO {

    Object updateByIdToStatusDbOnBlocking(String id) throws DBException;
}
