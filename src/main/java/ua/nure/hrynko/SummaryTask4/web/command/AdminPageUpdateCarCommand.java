package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlMenuDAO;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPageUpdateCarCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminPageUpdateCarCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        LOG.debug("UpdateCarCommand starts");
        MySqlMenuDAO updatedCar = MySqlMenuDAO.getInstance();

        String id = request.getParameter("carForUpdateButt");

        LOG.trace("Request parameter: id --> " + id);


        updatedCar.findCarToMenuDb(Integer.parseInt(id));

        LOG.trace("Update car to id: --> " + id);

        request.setAttribute("car", updatedCar.findCarToMenuDb(Integer.parseInt(id)));

        LOG.debug("UpdateCarCommand finished");
       return Path.PAGE_LIST_ADMIN_UPDATECAR;
    }

}

