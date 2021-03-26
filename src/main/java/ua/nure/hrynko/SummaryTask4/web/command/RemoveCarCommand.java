package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;

import ua.nure.hrynko.SummaryTask4.db.dao.MySqlCarsDAO;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveCarCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(RemoveCarCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        MySqlCarsDAO deletedCar = MySqlCarsDAO.getInstance();

        String id = request.getParameter("removeButt");

        LOG.trace("Request parameter: id --> " + id);


        deletedCar.deleteCarToCarsDb(Integer.parseInt(id));

        LOG.trace("Remove car to id: --> " + id);

        LOG.debug("Command finished");
        return Path.COMMAND_LIST_ADMIN;
    }

}

